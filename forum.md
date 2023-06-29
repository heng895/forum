# 社区首页

# 社区注册及登录

## 注册

## 会话管理

- cookie

  - 是服务器发送到浏览器，并保存在浏览器端的数据
  - 浏览器下次访问该服务器时，会自动携带该数据，将其发送给服务器

  ```java
  //cookie实例
      @RequestMapping(path = "/cookie/set",method = RequestMethod.GET)
      @ResponseBody
      public String setCookie(HttpServletResponse response){
          //create cookie
          Cookie cookie = new Cookie("code", ForumUtil.generateUUID());
          //set scope
          cookie.setPath("/forum/alpha");
          //set survival time (默认存在内存)
          cookie.setMaxAge(60*10);
          // send cookie
          response.addCookie(cookie);
          return "set cookie";
      }
  
      @RequestMapping(path = "/cookie/get",method = RequestMethod.GET)
      @ResponseBody
      public String getCookie(@CookieValue("code") String code){
          System.out.println(code);
          return "get cookie";
      }
  ```

  

- session

  - 是JavaEE的标准，用于在服务端记录客户端信息
  - 数据存放在服务端（更安全，但也会增加服务端的内存压力）

  ```java
  //session example
      @RequestMapping(path = "/session/set",method = RequestMethod.GET)
      @ResponseBody
      public String setSession(HttpSession session){
          session.setAttribute("id",2);
          session.setAttribute("name","Test");
          return "set session";
      }
  
      @RequestMapping(path = "/session/get",method = RequestMethod.GET)
      @ResponseBody
      public String getSession(HttpSession session){
          System.out.println(session.getAttribute("id"));
          System.out.println(session.getAttribute("name"));
          return "get session";
      }
  ```

- 使用cookie还是session
  - 一般存在Cookie中
  - 分布式部署下session会存在问题
    - 粘性session：交由同一服务期处理
    - 同步session：但性能差且服务器耦合
    - 共享session：一台服务器专门存储session，其他服务器访问获取，但存在单体故障问题
    - 存nosql数据库（redis）中：关系型数据库硬盘速度慢

## 生成验证码

- Kaptcha

  - 导入jar包
  - 编写配置类

  ```java
  @Configuration
  public class KaptchaConfig {
      @Bean
      public Producer kaptchaProducer() {
          Properties properties = new Properties();
          properties.setProperty("kaptcha.image.width", "100");
          properties.setProperty("kaptcha.image.height", "40");
          properties.setProperty("kaptcha.textproducer.font.size", "32");
          properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
          properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ");
          properties.setProperty("kaptcha.textproducer.char.length", "4");
          properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
  
          DefaultKaptcha kaptcha = new DefaultKaptcha();
          Config config = new Config(properties);
          kaptcha.setConfig(config);
          return kaptcha;
      }
  }
  ```

  - 生成随机字符和图片

  ```java
  @RequestMapping(path = "/kaptcha", method = RequestMethod.GET)
      public void getKaptcha(HttpServletResponse response, HttpSession session) {
          // 生成验证码
          String text = kaptchaProducer.createText();
          BufferedImage image = kaptchaProducer.createImage(text);
  
          // 将验证码存入session
          session.setAttribute("kaptcha", text);
  
          // 将图片输出给浏览器
          response.setContentType("image/png");
          try {
              OutputStream os = response.getOutputStream();
              ImageIO.write(image, "png", os);
          } catch (IOException e) {
              logger.error("响应验证码失败:" + e.getMessage());
          }
      }
  ```

  

## 登录

- 点击顶部登录链接，打开登录页面
- 登录
  - 验证账号、密码、验证码
  - 成功时生成登录凭证，发送给客户端
  - 失败跳转回登录页面
- 退出
  - 将登陆凭证修改为失效状态
  - 跳转至网站首页