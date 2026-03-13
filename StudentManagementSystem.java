
// 簡易學生管理系統

// 簡易學生管理系統
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;

class Student {
    private String id;
    private String name;
    private int age;

    // 建構子
    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Grtter
    public String getId() {
        return id;
    }

    public String toString() {
        return "學號: " + id + "，姓名: " + name + "，年齡: " + age;
    }
}

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner;

    public static void main(String[] args) {
        // 強制設定標準輸出/錯誤為 UTF-8，並使用 UTF-8 讀取標準輸入，
        // 可減少在 Windows 預設 OEM/ANSI 與檔案編碼不一致時出現的問號替代字元
        try {
            System.setOut(
                    new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true, "UTF-8"));
            System.setErr(
                    new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.err)), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // 若 JVM 不支援 UTF-8（極不可能），則使用預設輸出
        }

        // 以 UTF-8 讀取標準輸入，避免 Scanner 使用非預期預設字元集
        scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        boolean running = true;

        while (running) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    removeStudent();
                    break;
                case "3":
                    showAllStudents();
                    break;
                case "4":
                    System.out.println("系統結束，謝謝使用!!~");
                    running = false;
                    break;
                default:
                    System.out.println("請輸入有效的選項");
            }
        }
    }

    // 顯示功能選項
    public static void showMenu() {
        System.out.println("\n--- 學生管理系統 ---");
        System.out.println("1.新增學生");
        System.out.println("2.刪除學生");
        System.out.println("3.顯示所有學生");
        System.out.println("4.離開系統");
        System.out.println("請選擇操作:");
    }

    // 新增學生
    private static void addStudent() {
        System.out.println("請輸入學號:");
        String id = scanner.nextLine();
        System.out.println("請輸入姓名");
        String name = scanner.nextLine();
        System.out.println("請輸入年齡");
        int age = Integer.parseInt(scanner.nextLine());

        students.add(new Student(id, name, age));
        System.out.println("✔ 已新增學生！");
    }

    // 刪除學生
    private static void removeStudent() {
        System.out.println("請輸入要刪除學生的學號");
        String id = scanner.nextLine();

        boolean removed = students.removeIf(s -> s.getId().equals(id));
        if (removed) {
            System.out.println("學生已刪除");
        } else {
            System.out.println("X 查無此學號");
        }
    }

    // 顯示所有學生
    private static void showAllStudents() {
        System.out.println("\n--- 所有學生清單 ---");
        if (students.isEmpty()) {
            System.out.println("目前沒有學生資料");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }
}
