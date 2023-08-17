//import org.junit.Test;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @Author：cboss
// * @Date：2023/4/30
// */
//public class PasswordEncoderTest {
//    @Test
//    public void bCryptPasswordTest() {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String rawPassword = "637841";  //原始密码
//        String encodedPassword = passwordEncoder.encode(rawPassword); //加密后的密码
//        System.out.println("原始密码" + rawPassword);
//        System.out.println("加密之后的hash密码:" + encodedPassword);
//
//        encodedPassword = "$2a$10$5FxXzsoJxySP2qksVvoWmeNuX.KIOQQ8QYj75urJd2OmAomNn6xe.";
//        System.out.println(rawPassword + "是否匹配" + encodedPassword + ":"   //密码校验：true
//                + passwordEncoder.matches(rawPassword, encodedPassword));
//
//        System.out.println("654321是否匹配" + encodedPassword + ":"   //定义一个错误的密码进行校验:false
//                + passwordEncoder.matches("654321", encodedPassword));
//    }
//}
