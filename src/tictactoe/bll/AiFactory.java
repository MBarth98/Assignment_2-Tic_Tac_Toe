package tictactoe.bll;

public class AiFactory
{
    public static IAiModel createAI(AI_TYPES AiDiff) {
        return switch (AiDiff) {
            case DUMDUM_AI -> new DumDumAI();
            case CLEVER_AI -> new CleverAI();
            case CHEATING_AI -> new CheatingAI();
        };
    }

    public static IAiModel createAI()
    {
        return (instance == null) ? new CheatingAI() : instance;
    }

    public enum AI_TYPES
    {
        DUMDUM_AI, CLEVER_AI, CHEATING_AI;

        @Override
        public String toString() {
            return switch (this) {
                case CLEVER_AI -> "Clever AI";
                case DUMDUM_AI -> "DUMDUM AI";
                case CHEATING_AI -> "CHEATING AI";
            };
        }
    }

    private static IAiModel instance ;

    public static void setInstance(AI_TYPES type)
    {
        instance = switch (type) {
            case DUMDUM_AI -> new DumDumAI();
            case CLEVER_AI -> new CleverAI();
            case CHEATING_AI -> new CheatingAI();
        };
    }
}
