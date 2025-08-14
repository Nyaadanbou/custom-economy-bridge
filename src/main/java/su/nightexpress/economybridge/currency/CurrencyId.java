package su.nightexpress.economybridge.currency;

import org.jetbrains.annotations.NotNull;

public class CurrencyId {

    public static final String DUMMY = "dummy";

    public static final String XP_LEVELS = "xp_level";
    public static final String XP_POINTS = "xp_points";

    public static final String COINS_ENGINE_PREFIX  = "coinsengine_";
    public static final String ECONOMY_PREFIX       = "economy_";

    public static final String VAULT         = "vault";

    @NotNull
    public static String forCoinsEngine(@NotNull String id) {
        return COINS_ENGINE_PREFIX + id.toLowerCase();
    }

    @NotNull
    public static String forEconomy(@NotNull String id) {
        return ECONOMY_PREFIX + id.toLowerCase();
    }

    @NotNull
    public static String reroute(@NotNull String oldName) {
        if (oldName.equalsIgnoreCase("exp")) return XP_LEVELS;
        if (oldName.equalsIgnoreCase("level")) return XP_LEVELS;
        if (oldName.equalsIgnoreCase("xp")) return XP_POINTS;
        if (oldName.equalsIgnoreCase("money")) return VAULT;
        if (oldName.equalsIgnoreCase("economy")) return VAULT;

        return oldName;
    }
}
