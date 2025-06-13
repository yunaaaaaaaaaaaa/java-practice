import java.util.Scanner;

class Member {
    String name;
    int totalScore;
    int evaluationCount;

    public Member(String name) {
        this.name = name;
        this.totalScore = 0;
        this.evaluationCount = 0;
    }

    public void addScore(int score) {
        this.totalScore += score;
        this.evaluationCount++;
    }

    public double getAverage() {
        if (evaluationCount == 0) return 0;
        return (double) totalScore / evaluationCount;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("팀원 수를 입력하세요: ");
        int memberCount = scanner.nextInt();
        scanner.nextLine();

        Member[] members = new Member[memberCount];

        for (int i = 0; i < memberCount; i++) {
            System.out.print((i + 1) + "번째 팀원 이름: ");
            String name = scanner.nextLine();
            members[i] = new Member(name);
        }

        System.out.print("평가자 수를 입력하세요: ");
        int evaluatorCount = scanner.nextInt();
        scanner.nextLine();

        String[] evaluatedNames = new String[evaluatorCount];
        int evaluatedIndex = 0;

        for (int i = 0; i < evaluatorCount; i++) {
            System.out.print("\n" + (i + 1) + "번째 평가자 이름 입력: ");
            String evaluator = scanner.nextLine();

            // 팀원 목록에 있는지 검사
            int foundIndex = -1;
            for (int j = 0; j < memberCount; j++) {
                if (members[j].name.equals(evaluator)) {
                    foundIndex = j;
                    break;
                }
            }

            if (foundIndex == -1) {
                System.out.println("팀원 목록에 없는 이름입니다. 스킵합니다.");
                continue;
            }

            // 중복 검사 (배열로만 검사)
            int duplicateCheck = 0;
            for (int j = 0; j < evaluatedIndex; j++) {
                if (evaluatedNames[j].equals(evaluator)) {
                    duplicateCheck = 1;
                    break;
                }
            }
            if (duplicateCheck == 1) {
                System.out.println("이미 평가한 사람입니다. 스킵합니다.");
                continue;
            }

            // 평가 진행
            for (int j = 0; j < memberCount; j++) {
                if (members[j].name.equals(evaluator)) {
                    continue;
                }

                int score = 0;
                while (true) {
                    System.out.print(members[j].name + "의 점수 (1~5): ");
                    score = scanner.nextInt();
                    if (score >= 1 && score <= 5) {
                        break;
                    }
                    System.out.println("1~5 사이 숫자 입력하세요!");
                }
                members[j].addScore(score);
            }
            scanner.nextLine();
            evaluatedNames[evaluatedIndex] = evaluator;
            evaluatedIndex++;
        }

        System.out.println("\n--- 최종 평가 결과 ---");
        for (int i = 0; i < memberCount; i++) {
            System.out.printf("%s: 총점 %d, 평균 %.2f점\n", members[i].name, members[i].totalScore, members[i].getAverage());
        }

        System.out.println("\n--- 경고: 총점 5점 미만인 사람 ---");
        int warningCount = 0;
        for (int i = 0; i < memberCount; i++) {
            if (members[i].totalScore < 5) {
                System.out.println(members[i].name + ": 총점 " + members[i].totalScore + "점");
                warningCount++;
            }
        }
        if (warningCount == 0) {
            System.out.println("5점 미만인 사람이 없습니다.");
        }
    }
}
