package pkq.service;

public interface MyUser {
    public static  String nihao = "Nihao";
    default    String getNames(){
        return  "你好";
    }
}
