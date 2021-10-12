package tictactoe.bll;

public class AiFactory
{
    public static IAiModel createAI(AiFactory.AI_DIFF AiDiff) {
        return switch (AiDiff) {
            case DUMDUM_AI -> new DumDumAI();
            case CLEVER_AI -> new CleverAI();
        };
    }

    public enum AI_DIFF {
        DUMDUM_AI, CLEVER_AI;

        @Override
        public String toString() {
            return switch (this) {
                case CLEVER_AI -> "Clever AI";
                case DUMDUM_AI -> "DUMDUM AI";
            };
        }
    }
}
