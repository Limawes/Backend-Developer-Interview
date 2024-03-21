package br.com.mobiauto.security.auth;

import br.com.mobiauto.domain.model.UsuariosModel;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class LoginAuth {
  private static UsuariosModel user = new UsuariosModel();

  public LoginAuth(UsuariosModel user) {
    LoginAuth.user = user;
  }

  public static boolean validarLogin(String email, String password) {
    String hashedPassword = hashPassword(password);



    if(user.getEmail().isEmpty() && user.getSenha().isEmpty()){
      user.setEmail(email);
      user.setSenha(hashedPassword);
    }
    return false;
  }

  public String message(String message){
    if(validarLogin(user.getEmail(), user.getSenha())){
      message = "Login realizado com sucesso!";
    }
    return message;
  }

  private static String hashPassword(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(password.getBytes());

      // Converter a senha em hexadecimal
      StringBuilder hexString = new StringBuilder();
      for (byte hashByte : hashBytes) {
        String hex = Integer.toHexString(0xff & hashByte);
        if (hex.length() == 1)
          hexString.append('0');
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      log.info(String.valueOf(e));
      return null;
    }
  }
}
