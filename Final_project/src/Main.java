import java.util.Scanner;

class Member {
    String name;
    int score;

    public Member(String name) {
        this.name = name;
        this.score = 0;
    }

    public void addScore(int score) {
        this.score += score;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("팀원 수를 입력하세요: ");
        int memberCount = scanner.nextInt();
        scanner.nextLine(); // 엔터 처리

        Member[] members = new Member[memberCount];

        for (int i = 0; i < memberCount; i++) {
            System.out.print((i + 1) + "번째 팀원 이름: ");
            String name = scanner.nextLine();
            members[i] = new Member(name);
        }

        System.out.print("평가자 이름을 입력하세요: ");
        String evaluator = scanner.nextLine();

        for (int i = 0; i < memberCount; i++) {
            if (members[i].name.equals(evaluator)) {
                continue; // 본인 제외
            }

            int score;
            while (true) {
                System.out.print(members[i].name + "의 점수 (1~5): ");
                score = scanner.nextInt();
                if (score >= 1 && score <= 5) {
                    break;
                }
                System.out.println("1~5 사이 숫자 입력하세요!");
            }
            members[i].addScore(score);
        }

        System.out.println("\n--- 평가 결과 ---");
        for (int i = 0; i < memberCount; i++) {
            System.out.println(members[i].name + ": " + members[i].score + "점");
        }
    }
}
