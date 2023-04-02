package data.inputReader;
import com.github.javafaker.Faker;
import data.factory.models.InnerUser;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/*
password min length -- 12
login

 */
public class InnerUserFactory {
    private HashMap<String, ArrayList<String>> parameters = new HashMap<>();
    private static final Faker FAKER = new Faker();
    String FILE_PATH;

    public InnerUserFactory(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

    private void readXlsxFile() throws IOException {
        Reader reader = new Reader(FILE_PATH);
        parameters = reader.getHashMapFromXlsxFile();
    }

    public void userGeneration(ArrayList<InnerUser> innerUserList) throws IOException {
        readXlsxFile();
        int i = 0;
        Map.Entry<String, ArrayList<String>> first = parameters.entrySet().iterator().next();
        int parametr_num = first.getValue().size();

        while(i < parametr_num){
            InnerUser innerUser = new InnerUser();
            for(Map.Entry<String, ArrayList<String>> entry : parameters.entrySet()) {
                String key = entry.getKey();
                ArrayList<String> value = entry.getValue();
                switch (key) {
                    case ("name") -> innerUser.setName(getString(value.get(i)));
                    case ("lastname") -> innerUser.setLastname(getString(value.get(i)));
                    case ("login") -> innerUser.setLogin(getString(value.get(i)));
                    case ("password") -> innerUser.setPassword(getString(value.get(i)));
                    case ("email") -> innerUser.setEmail(getString(value.get(i)));
                    case ("city") -> innerUser.setCity(getString(value.get(i)));
                    case ("phone") -> innerUser.setPhone(getString(value.get(i)));
                    case ("jobTitle") -> innerUser.setJobTitle(getString(value.get(i)));
                    case ("address") -> innerUser.setAddress(getString(value.get(i)));
                    case ("zipCode") -> innerUser.setZipCode(getString(value.get(i)));
                    case ("fax") -> innerUser.setFax(getString(value.get(i)));
                    case ("isAdmine") -> innerUser.setIsAdmine(getBoolean(value.get(i)));
                    case ("isEmployee") -> innerUser.setIsEmployee(getBoolean(value.get(i)));
                }
            }
            innerUserList.add(innerUser);
            i++;
        }

    }

    private String getString(String keyword) {
        String paramName;
        int size;
        if (keyword.contains("_")) {
            String[] tmp = keyword.split("_");
            paramName = tmp[0];
            size = Integer.parseInt(tmp[1]);
            return switch (paramName) { // одинаковве данные
                case ("name") -> Stream.generate(() -> String.valueOf("a")).limit(size).collect(Collectors.joining());
                case ("phone") -> Stream.generate(() -> String.valueOf("a")).limit(size).collect(Collectors.joining());
                case ("email") -> Stream.generate(() -> String.valueOf("a")).limit(size).collect(Collectors.joining());
                case ("address") -> Stream.generate(() -> String.valueOf("a")).limit(size).collect(Collectors.joining());
                case ("zipcode") -> Stream.generate(() -> String.valueOf("a")).limit(size).collect(Collectors.joining());
                case ("login") -> Stream.generate(() -> String.valueOf("a")).limit(size).collect(Collectors.joining());
                case ("lastname") -> Stream.generate(() -> String.valueOf("a")).limit(size).collect(Collectors.joining());
                case ("password") -> getPassword(size);
                default -> "";

            };
        } else {
            size = 12;

            return switch (keyword) {
                case ("space") -> StringUtils.repeat(" ", size);
                case ("name") -> FAKER.name().name();
                case ("email") -> FAKER.internet().emailAddress();
                case ("address") -> FAKER.address().fullAddress();
                case ("zipCode") -> FAKER.address().zipCode();
                case ("city") -> FAKER.address().city();
                case ("phone") -> FAKER.phoneNumber().phoneNumber();
                case ("jobTitle") -> FAKER.job().title();
                case ("lastname") -> FAKER.name().lastName();
                case ("login") -> FAKER.name().username();
                case ("password") -> getPassword(size);
                case ("fax") -> FAKER.phoneNumber().phoneNumber();
                default -> "";
            };
        }
    }

    private Boolean getBoolean(String keyword) {
        return keyword.equals("true");
    }

    private String getPassword(int length){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

}
