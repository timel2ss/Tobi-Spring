package Calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calcSum(String filepath) throws IOException {
        return fileReadTemplate(filepath, new LineCallback<Integer>() {
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.parseInt(line);
            }
        }, 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        return fileReadTemplate(filepath, new LineCallback<Integer>() {
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.parseInt(line);
            }
        }, 1);
    }

    public String concatenate(String filepath) throws IOException {
        return fileReadTemplate(filepath, new LineCallback<String>() {
            public String doSomethingWithLine(String line, String value) {
                return value + line;
            }
        }, "");
    }

    public <T> T fileReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            T res = initVal;
            String line = null;
            while((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            throw e;
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
