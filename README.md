# Usando cljs em um projeto JS

Passo a passo de como integrar clojureScript dentro do ecossistema de um projeto JS.

#### Instalando dependencias
Necessário uma versão atualizada do java([Você pode baixar por aqui](https://www.java.com/pt_BR/download/)) e do npx, que é um pacote para executar binarios da [npm]([Aqui](https://www.npmjs.com/get-npm)).
Para instalar o npmx pela npm rode:
`npm install -g npx`

Verifique se as dependências foram corretamente instaladas:
```bash
$ java -version
openjdk version "1.8.0_192"
OpenJDK Runtime Environment (build 1.8.0_192-b26)
OpenJDK 64-Bit Server VM (build 25.192-b26, mixed mode)
$ npx --version
6.4.1
```

#### Arquivos de configuração

- `shadow-cljs.edn`: é nele que estará as dependencias/configuração da parte
clojure do seu projeto

```clojure
{:dependencies [[org.clojure/clojurescript "1.10.339"]
                [reagent/reagent "0.8.1"]]

 :source-paths ["src"]

 :builds       {:app {:target     :browser
                      :output-dir "public/js"
                      :asset-path "/js"
                      :modules    {:main {:entries [jstocljs.core]}}}}}
```

- **\[OPCIONAL\]** Crie um `project.clj`. Algumas IDE's/editores de texto precisam
dele para funcionar

```clojure
(defproject jstocljs "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.0-beta4"]
                 [org.clojure/clojurescript "1.10.339"]
                 [reagent/reagent "0.8.1"]])

```

#### Primeiro clojurescript

- Crie o arquivo `src/jstocljs/core.cljs`

```clojure
(ns jstocljs.core)

(defn ^:export MyFirstFn
  [x]
  (* x x))
;; veja o src/jstocljs/core.cljs no git para um exemplo completo
```

- Coloque o compilador do clojurescript para rodar no modo interativo

(ao editar, ele será compilado automagicamente)
```bash
npx shadow-cljs watch npm
```

DICA: O javascript "gerado" por esse `jstocljs.core` está em
`node_modules/shadow-cljs/jstocljs.core.js` e é um arquivo JS legível

- Em outro terminal, inicie o projeto como de costume usando `yarn` ou `npm`

**TODO**: atualizar as configurações do `webpack` para quando houver

```bash
yarn install

yarn start
```

- Importe o clojurescript em qualquer lugar no JS

o clojurescript estará "dentro" do shadow-cljs. Os imports podem ser feitos
via ES6 ou `require`.

```javascript
import {MyFirstFn} from 'shadow-cljs/jstocljs.core';
```


#### Como buildar para produção
```bash
npx shadow-cljs compile npm && yarn build
```

# TODO
- [ ] é possivel importar JS a partir do cljs
- [ ] explicar melhor configurações de build avançadas
- [ ] conectar um repl?!
- [ ] usar o root component como CLJS
- [ ] Documentação em DOCZ e um github pages
- [ ] Adicionar devcards para componentes JS e CLJS ?!

