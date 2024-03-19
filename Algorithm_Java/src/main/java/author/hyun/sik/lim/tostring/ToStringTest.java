package author.hyun.sik.lim.tostring;
class ToStringTest {
    public static void main(String[] args) {
        ToStringSample test = new ToStringSample();

        System.out.println(test.toString());

    }
}



class ToStringSample {
    String no = "1";
    String str = "str";

    String key = "key";

    @Override
    public String toString() {
        return "ToStringSample{" +
                "no='" + no + '\'' +
                ", str='" + str + '\'' +
                ", key='" + key + '\'' +
                '}';
    }


}