// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parser: 'babel-eslint',
  parserOptions: {
    sourceType: 'module'
  },
  env: {
    browser: true,
  },
  // https://github.com/standard/standard/blob/master/docs/RULES-en.md
  extends: 'standard',
  // required to lint *.vue files
  plugins: [
    'html'
  ],
  // add your custom rules here
  'rules': {
    // allow paren-less arrow functions
    'arrow-parens': 0,
    // allow async-await
    'generator-star-spacing': 0,
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0,
    'no-tabs':'off',
    "indent": 0,//缩进风格
    "comma-spacing": [0,1],//逗号前后的空格
    "spaced-comment": 1,//注释风格要不要有空格什么的
    "eol-last": 0,//文件以单一的换行符结束
    "space-before-function-paren": [0, "always"],//函数定义时括号前面要不要有空格
    "semi": [2, "always"],//语句强制分号结尾
    //"quotes": [1, "single"],//引号类型
  }
}
