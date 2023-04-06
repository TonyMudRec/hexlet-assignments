package exercise;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;


// BEGIN
class App {
    public static void main(String[] args) throws IOException {
        String configFile = "[foo:bar]\n" +
                "environment=\"X_FORWARDED_var1=111\"\n" +
                "\n" +
                "[bar:baz]\n" +
                "environment=\"key2=true,X_FORWARDED_var2=123\"\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make prepare'\n" +
                "\n" +
                "[baz:foo]\n" +
                "key3=\"value3\"\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make prepare'\n" +
                "\n" +
                "[program:prepare]\n" +
                "environment=\"key5=value5,X_FORWARDED_var3=value,key6=value6\"\n" +
                "\n" +
                "[program:start]\n" +
                "environment=\"pwd=/temp,user=tirion\"\n" +
                "\n" +
                "[program:options]\n" +
                "environment=\"X_FORWARDED_mail=tirion@google.com,X_FORWARDED_HOME=/home/tirion,language=en\"\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make environment'\n" +
                "\n" +
                "[empty]\n" +
                "command=\"X_FORWARDED_HOME=/ cd ~\"\n";
        System.out.println(getForwardedVariables(configFile));
    }

    public static String getForwardedVariables(String config) {
        StringBuilder sb = new StringBuilder();
        String[] arrayOfConfigElements = config.replaceAll("\"", "").split("\n");
        for (String line : arrayOfConfigElements) {
            if (line.startsWith("environment")) {
                Arrays.stream((line.substring(12).split(",")))
                        .filter(i -> i.contains("X_FORWARDED_"))
                        .map(i -> i.replace("X_FORWARDED_", ""))
                        .forEach(i -> sb.append(i).append(","));
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
//END
