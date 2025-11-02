package lotto.view;

import lotto.Lotto;
import lotto.domain.Rank;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.printf("\n%d개를 구매했습니다.\n", lottos.size());
        for (Lotto lotto : lottos) {
            // Lotto.getNumbers()는 이미 정렬된 리스트를 반환
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatistics(Map<Rank, Integer> statistics) {
        NumberFormat formatter = NumberFormat.getInstance(); // 콤마 포맷

        System.out.println("\n당첨 통계");
        System.out.println("---");

        // Enum 순서(FIFTH -> FIRST)대로 출력하기 위해
        System.out.printf("3개 일치 (%s원) - %d개\n", formatter.format(Rank.FIFTH.getPrize()), statistics.getOrDefault(Rank.FIFTH, 0));
        System.out.printf("4개 일치 (%s원) - %d개\n", formatter.format(Rank.FOURTH.getPrize()), statistics.getOrDefault(Rank.FOURTH, 0));
        System.out.printf("5개 일치 (%s원) - %d개\n", formatter.format(Rank.THIRD.getPrize()), statistics.getOrDefault(Rank.THIRD, 0));
        System.out.printf("5개 일치, 보너스 볼 일치 (%s원) - %d개\n", formatter.format(Rank.SECOND.getPrize()), statistics.getOrDefault(Rank.SECOND, 0));
        System.out.printf("6개 일치 (%s원) - %d개\n", formatter.format(Rank.FIRST.getPrize()), statistics.getOrDefault(Rank.FIRST, 0));
    }

    public void printProfitRate(double rate) {
        System.out.printf("총 수익률은 %.1f%%입니다.\n", rate);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
