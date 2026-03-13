

// 簡易學生管理系統
//
// 此程式為教學用的命令列互動範例，包含：新增、刪除與顯示學生資料。

import java.nio.charset.StandardCharsets; // 可用於字元編碼處理（目前程式未直接使用）
import java.util.ArrayList; // 用來儲存學生清單
import java.util.Scanner; // 用來讀取使用者從 stdin 的輸入

/*
 * Student 類別：封裝學生資料（學號、姓名、年齡）
 * - 提供建構子建立物件
 * - 提供 getId() 供外部依學號搜尋/比對
 * - 覆寫 toString() 以人可讀格式輸出學生資料
 */
class Student {
    private String id; // 學號（字串以支援前導零）
    private String name; // 學生姓名
    private int age; // 年齡

    // 建構子：用學號、姓名、年齡建立新的 Student 物件
    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // 取得學號（供刪除功能比對使用）
    public String getId() {
        return id;
    }

    // 覆寫 toString，當 System.out.println(student) 時會以此格式顯示
    public String toString() {
        return "學號: " + id + "，姓名: " + name + "，年齡: " + age;
    }
}

/*
 * StudentManagementSystem 主程式
 * - 使用一個 ArrayList<Student> 儲存目前的學生清單
 * - 透過 Scanner 從標準輸入讀取使用者命令
 * - 提供互動式選單：1.新增 2.刪除 3.顯示 4.離開
 */
public class StudentManagementSystem {
    // 存放學生物件的容器
    private static ArrayList<Student> students = new ArrayList<>();

    // Scanner 用來從標準輸入讀取使用者輸入（nextLine() 逐行讀）
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true; // 主迴圈開關

        // 主迴圈：顯示選單並根據使用者輸入執行對應功能
        while (running) {
            showMenu(); // 顯示功能選單
            String choice = scanner.nextLine(); // 讀取使用者選項（以字串處理）

            switch (choice) {
                case "1":
                    addStudent(); // 新增學生
                    break;
                case "2":
                    removeStudent(); // 刪除學生（以學號比對）
                    break;
                case "3":
                    showAllStudents(); // 顯示所有學生
                    break;
                case "4":
                    System.out.println("系統結束，謝謝使用!!~");
                    running = false; // 結束主迴圈，程式結束
                    break;
                default:
                    // 非法輸入時的提醒
                    System.out.println("請輸入有效的選項");
            }
        }
    }

    // 顯示主選單的文字介面
    public static void showMenu() {
        System.out.println("\n--- 學生管理系統 ---");
        System.out.println("1.新增學生");
        System.out.println("2.刪除學生");
        System.out.println("3.顯示所有學生");
        System.out.println("4.離開系統");
        System.out.println("請選擇操作:");
    }

    // 新增學生：逐一詢問學號、姓名、年齡，並建立 Student 物件加入 students 清單
    private static void addStudent() {
        System.out.println("請輸入學號:");
        String id = scanner.nextLine(); // 以字串讀取學號，避免前導零被剃除

        System.out.println("請輸入姓名");
        String name = scanner.nextLine(); // 讀取姓名（支援中文字串）

        System.out.println("請輸入年齡");
        // 讀取年齡並轉為整數；此處未做輸入驗證，若輸入非數字會丟出 NumberFormatException
        int age = Integer.parseInt(scanner.nextLine());

        // 建立並加入學生清單
        students.add(new Student(id, name, age));
        System.out.println("✔ 已新增學生！");
    }

    // 刪除學生：以使用者輸入的學號為條件移除清單中的學生
    private static void removeStudent() {
        System.out.println("請輸入要刪除學生的學號");
        String id = scanner.nextLine();

        // 使用 removeIf 以 lambda 表示式比對學號並移除符合者，返回是否有移除成功
        boolean removed = students.removeIf(s -> s.getId().equals(id));
        if (removed) {
            System.out.println("學生已刪除");
        } else {
            System.out.println("X 查無此學號");
        }
    }

    // 顯示所有學生：若清單為空，印出提示；否則逐筆列印學生物件的 toString() 結果
    private static void showAllStudents() {
        System.out.println("\n--- 所有學生清單 ---");
        if (students.isEmpty()) {
            System.out.println("目前沒有學生資料");
        } else {
            for (Student s : students) {
                System.out.println(s); // 會呼叫 Student.toString()，顯示學號/姓名/年齡
            }
        }
    }
}

