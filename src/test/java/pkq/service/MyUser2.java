package pkq.service;

public interface MyUser2 {
    public static  String nihao = "Nihao";
    default    String getNames(){
        return  "你不好";
    }
}
