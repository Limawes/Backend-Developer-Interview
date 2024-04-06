package br.com.mobiauto.service.execeptions;

public class NoDataFoundException extends RuntimeException{

  public NoDataFoundException(){
    super("Nenhum dado encontrado!");
  }

  public NoDataFoundException(String msg){
    super(msg);
  }
}
