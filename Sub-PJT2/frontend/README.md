# example

## 초기 세팅

1. 마켓(ctrl+shift+x)에서 ESLint, Prettier 설치

2. 설치 후 마켓에서 Prettier검색 후 톱니바퀴 클릭 -> 사용안함 클릭 -> 로드 필요 클릭

3. ctrl + , -> 설정 검색에 eslint -> 쭉 내려서 eslint: validate 항복에서 settings.json에서 편집 클릭후, 다음과 같이 추가 or 작성

   ```json
   {
   	// 위에 내용 생략
       
   	"eslint.validate": [
     
       {
         "language": "vue",
         "autoFix": true
       },
       {
         "language": "javascript",
         "autoFix": true
       },
       {
         "language": "javascriptreact",
         "autoFix": true
       },
       {
         "language": "typescript",
         "autoFix": true
       },
       {
         "language": "typescriptreact",
         "autoFix": true
       }
     ],
     "editor.codeActionsOnSave": {
       "source.fixAll.eslint": true
     },
     "eslint.workingDirectories": [
       {"mode": "auto"}
     ],
   }
   ```

4. 이제 Vue에서 ctrl + s로 저장할 때마다 자동으로 문법검사와 코드를 자동 정렬 시켜줄 수 있습니다.


## Project setup

```
yarn install
```

### Compiles and hot-reloads for development
```
yarn serve
```

### Compiles and minifies for production
```
yarn build
```

### Lints and fixes files
```
yarn lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
