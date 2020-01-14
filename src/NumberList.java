import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class NumberList {
    public static void main(String[] args) {

        TreeMap<String, String> phoneAndUser = new TreeMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("*** Телефонная книга *** \nДля добавления контакта введите его имя или номер в формате" +
                " <<79003335535>> отдельно или вместе!");

        for (; ; ) {

            String Listing = scanner.nextLine();
            String[] splitList = Listing.split("\\s+", 2);

            String FirstElement = splitList[0].trim();


            if (splitList.length == 1) {
                if (checkNameCorrect(splitList[0]) && !splitList[0].equals("LIST")) {
                    String name = splitList[0];
                    if (phoneAndUser.isEmpty()) {
                        System.out.println("Введите номер телефона для создания нового контакта");
                        String phone = scanner.nextLine();
                        if (checkNumberPhoneCorrect(phone)) {
                            phoneAndUser.put(name, phone);
                            System.out.println("Контакт " + name + " успешно создан");
                        }
                    } else {
                        if (phoneAndUser.containsKey(splitList[0])) {
                            for (Map.Entry<String, String> entries : phoneAndUser.entrySet()) {
                                System.out.println("Контакт " + splitList[0] + " уже существует. Номер : " + entries.getValue() +
                                        "\nЕсли вы хотите перезаписать контакт, то введите новый номер телефона, или введите <<EXIT>> для выхода");
                                String phone = scanner.nextLine();
                                if (checkNumberPhoneCorrect(phone)) {
                                    phoneAndUser.remove(splitList[0]);
                                    System.out.println("Контакт " + name + " изменен. Новый номер: " + phone);
                                    phoneAndUser.put(name, phone);
                                    break;
                                } else if (phone.equals("EXIT")) {
                                    System.out.println("Для добавление нового контакта введите его номер или имя");
                                    break;
                                } else {
                                    System.out.println("Ошибка");
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Введите номер телефона для создания нового контакта");
                            String phone = scanner.nextLine();
                            if (checkNumberPhoneCorrect(phone)) {
                                phoneAndUser.put(name, phone);
                                System.out.println("Контакт " + name + " успешно создан");
                            }
                        }
                    }
                }else if (checkNumberPhoneCorrect(splitList[0])) {
                    String phone = splitList[0];
                    if (phoneAndUser.isEmpty()) {
                        System.out.println("Введите имя для создания нового контакта");
                        String name = scanner.nextLine();
                        if (checkNameCorrect(name)) {
                            phoneAndUser.put(name, phone);
                            System.out.println("Контакт " + name + " успешно создан");
                        }
                    } else {
                        if (phoneAndUser.containsValue(splitList[0])) {
                            for (Map.Entry<String, String> entries : phoneAndUser.entrySet()) {
                                if (entries.getValue().contains(splitList[0])) {
                                    System.out.println("Контакт " + entries.getKey() + " уже существует. Номер : " + entries.getValue() +
                                            "\nЕсли вы хотите перезаписать контакт, то введите новое имя, или введите <<EXIT>> для выхода");
                                    String name = scanner.nextLine();
                                    if (checkNameCorrect(name) && !name.equals("EXIT")) {
                                        phoneAndUser.remove(entries.getKey());
                                        System.out.println("Контакт с номером - " + phone + " изменен на <<" + name + ">>");
                                        phoneAndUser.put(name, phone);
                                        break;
                                    } else if (name.equals("EXIT")) {
                                        System.out.println("Для добавление нового контакта введите его номер или имя");
                                        break;
                                    } else {
                                        System.out.println("Ошибка");
                                        break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Введите имя для создания нового контакта");
                            String name = scanner.nextLine();
                            if (checkNameCorrect(name)) {
                                phoneAndUser.put(name, phone);
                                System.out.println("Контакт " + name + " успешно создан");
                            }
                        }
                    }
                }else{
                    System.out.println("Ошибка");
                }
            } else if (splitList.length == 2) {
                if (checkNumberPhoneCorrect(splitList[0]) && checkNameCorrect(splitList[1])) {
                    if (phoneAndUser.isEmpty()) {
                        phoneAndUser.put(splitList[1], splitList[0]);
                        System.out.println("Контакт " + splitList[1] + " добавлен под номером " + splitList[0]);
                    } else {
                        if (phoneAndUser.containsKey(splitList[1])) {
                            for (Map.Entry<String, String> entries : phoneAndUser.entrySet()) {
                                if (entries.getKey().contains(splitList[1])) {
                                    System.out.println("Контакт " + splitList[1] + " уже существует" +
                                            "\nДля редактирования контакта введите имя или номер отдельно");
                                    break;
                                }
                            }
                        } else {
                            phoneAndUser.put(splitList[1], splitList[0]);
                            System.out.println("Контакт " + splitList[1] + " добавлен под номером " + splitList[0]);
                        }
                    }
                } else if (checkNumberPhoneCorrect(splitList[1]) && checkNameCorrect(splitList[0])) {
                    if (phoneAndUser.isEmpty()) {
                        phoneAndUser.put(splitList[0], splitList[1]);
                        System.out.println("Контакт " + splitList[0] + " добавлен под номером " + splitList[1]);
                    } else {
                        if (phoneAndUser.containsKey(splitList[0])) {
                            for (Map.Entry<String, String> entries : phoneAndUser.entrySet()) {
                                if (entries.getKey().contains(splitList[0])) {
                                    System.out.println("Контакт " + splitList[0] + " уже существует" +
                                            "\nДля редактирования контакта введите имя или номер отдельно");
                                    break;
                                }
                            }
                        } else {
                            phoneAndUser.put(splitList[0], splitList[1]);
                            System.out.println("Контакт " + splitList[0] + " добавлен под номером " + splitList[1]);
                        }
                    }
                } else {
                    System.out.println("Ошибка");
                }
            }
            if (FirstElement.equals("LIST")) {
                if (phoneAndUser.isEmpty()) {
                    System.out.println("Список контактов пуст");
                } else {
                    printMap(phoneAndUser);
                    continue;
                }
            }
        }
    }

    private static void printMap(TreeMap<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println("Владелец: " + key + " номер - " + map.get(key));
        }
    }

    private static boolean checkNumberPhoneCorrect(String phone) {
        return phone.matches("(7)\\d{10}");
    }

    private static boolean checkNameCorrect(String name) {
        return name.matches("\\D+");
    }
}

