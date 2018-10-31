# Usando cljs em um projeto JS

- verifique suas dependencias
```bash
$ java -version
openjdk version "1.8.0_192"
OpenJDK Runtime Environment (build 1.8.0_192-b26)
OpenJDK 64-Bit Server VM (build 25.192-b26, mixed mode)
$ npx --version
6.4.1
```

- crie o arquivo de configuração `shadow-cljs.edn`

```clojure
{:dependencies [[org.clojure/clojurescript "1.10.339"]
                [reagent/reagent "0.8.1"]]

 :source-paths ["src"]

 :builds       {:app {:target     :browser
                      :output-dir "public/js"
                      :asset-path "/js"
                      :modules    {:main {:entries [jstocljs.core]}}}}}
```

- (OPCIONAL) Crie um `project.clj` para sua IDE não surtar

```clojure
(defproject jstocljs "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.0-beta4"]
                 [org.clojure/clojurescript "1.10.339"]
                 [reagent/reagent "0.8.1"]])

```

- Coloque o compilador cljs no modo watch

```bash
npx shadow-cljs watch npm
```

- Em outro terminal, inicie o `yarn` como de costume
*TODO*: atualizar as configurações do `webpack` para quando houver
mudanças em `shadow-cljs` ele acionar o "hot-reload"
```bash
yarn start
```

- crie o arquivo `src/jstocljs/core.cljs`
DICA: O javascript "gerado" por esse `jstocljs.core` está em
`node_modules/shadow-cljs/jstocljs.core.js` e é um arquivo JS legivel


- Na hora do build de produção:
```bash
npx shadow-cljs compile npm && yarn build
```

# TODO
- é possivel importar JS a partir do cljs
- explicar melhor configurações de build avançadas
- conectar um repl?!
- usar o root component como CLJS
