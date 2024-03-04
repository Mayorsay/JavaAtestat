import java.util.*;

class Laptop {
    private String model;
    private int ramGb;
    private int storageGb;
    private String os;
    private String color;

    // Конструктор
    public Laptop(String model, int ramGb, int storageGb, String os, String color) {
        this.model = model;
        this.ramGb = ramGb;
        this.storageGb = storageGb;
        this.os = os;
        this.color = color;
    }

    // методы
    // геттеры для получения значений полей
    public String getModel() {
        return model;
    }

    public int getRamGb() {                         // исправление к laptop (обьявление методов)
        return ramGb;
    }

    public int getStorageGb() {
        return storageGb;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
}
class Main {
    public static void main(String[] args) {
        // создание множества ноутбуков
        Set<Laptop> laptops = new HashSet<>();
        // множество ноутбуков:
        laptops.add(new Laptop("LowPC", 8, 512, "Windows", "Белый"));
        laptops.add(new Laptop("MediumPC", 16, 1024, "MacOS", "Черный"));
        laptops.add(new Laptop("MediumPC", 32, 2048, "MacOS", "Черный"));
        laptops.add(new Laptop("MediumPC", 32, 2048, "Windows", "Белый"));
        laptops.add(new Laptop("HighPC", 64, 4096, "Linux", "Серебряный"));



        // Метод фильтрации
        filterLaptops(laptops);
    }

    // Метод фильтрации
    public static void filterLaptops(Set<Laptop> laptops) {
        // Критерии фильтрации
        Map<String, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");   //8, 16, 32, 64
        System.out.println("2 - Объем ЖД"); //512, 1024, 2048, 4096
        System.out.println("3 - Операционная система"); //Windows, MacOS, Linux
        System.out.println("4 - Цвет"); //Белый, Черный, Серебряный
        System.out.println("Введите номер критерия: ");
        int criterion = scanner.nextInt();

        switch (criterion) {
            case 1:
                System.out.println("Введите минимальный объем ОЗУ: ");
                int minRam = scanner.nextInt();
                filters.put("ramGb", minRam);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД: ");
                int minStorage = scanner.nextInt();
                filters.put("storageGb", minStorage);
                break;
            case 3:
                System.out.println("Введите операционную систему: ");
                String os = scanner.next();
                filters.put("os", os);
                break;
            case 4:
                System.out.println("Введите цвет: ");
                String color = scanner.next();
                filters.put("color", color);
                break;
            default:
                System.out.println("Некорректный номер критерия");
                return;
        }

        // фильтрация ноутбуков
        for (Laptop laptop : laptops) {
            if (passesFilter(laptop, filters)) {
                System.out.println(laptop.getModel());
            }
        }
    }

    // метод проверки соответствия фильтру
    public static boolean passesFilter(Laptop laptop, Map<String, Object> filters) {
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String criterion = entry.getKey();
            Object value = entry.getValue();

            switch (criterion) {
                case "ramGb":
                    if (laptop.getRamGb() < (int)value) {
                        return false;
                    }
                    break;
                case "storageGb":
                    if (laptop.getStorageGb() < (int)value) {
                        return false;
                    }
                    break;
                case "os":
                    if (!laptop.getOs().equals(value)) {
                        return false;
                    }
                    break;
                case "color":
                    if (!laptop.getColor().equals(value)) {
                        return false;
                    }
                    break;
                default:
                    // неизвестный критерий
                    return false;
            }
        }
        return true;
    }
}