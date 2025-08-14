package su.nightexpress.economybridge.currency.impl;

import cc.mewcraft.economy.api.EconomyProvider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.economybridge.api.Currency;
import su.nightexpress.economybridge.currency.CurrencyId;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class EconomyCurrency implements Currency {

    private final String id;
    private final cc.mewcraft.economy.api.Currency currency;

    public EconomyCurrency(cc.mewcraft.economy.api.Currency currency) {
        this.id = CurrencyId.forEconomy(currency.getName());
        this.currency = currency;
    }

    @NotNull
    public static Set<EconomyCurrency> getCurrencies() {
        return EconomyProvider.get().getLoadedCurrencies().stream().map(EconomyCurrency::new).collect(Collectors.toSet());
    }

    @Override
    public boolean canHandleDecimals() {
        return this.currency.isDecimalSupported();
    }

    @Override
    public boolean canHandleOffline() {
        return true;
    }

    @Override
    public @NotNull String format(double amount) {
        return this.currency.fancyFormat(amount);
    }

    @Override
    public @NotNull String formatValue(double amount) {
        return this.currency.simpleFormat(amount);
    }

    @Override
    public @NotNull String getOriginalId() {
        return this.currency.getUuid().toString();
    }

    @Override
    public @NotNull String getInternalId() {
        return this.id;
    }

    @Override
    public @NotNull String getName() {
        return this.currency.getName();
    }

    @Override
    public @NotNull String getDefaultName() {
        return this.getName();
    }

    @Override
    public @NotNull String getFormat() {
        return this.currency.simpleFormat(0.0);
    }

    @Override
    public @NotNull ItemStack getIcon() {
        return new ItemStack(Material.EMERALD);
    }

    @Override
    public @NotNull ItemStack getDefaultIcon() {
        return new ItemStack(Material.SUNFLOWER);
    }

    @Override
    public double getBalance(@NotNull Player player) {
        return EconomyProvider.get().getBalance(player.getUniqueId(), this.currency);
    }

    @Override
    public double getBalance(@NotNull UUID playerId) {
        return EconomyProvider.get().getBalance(playerId, this.currency);
    }

    @Override
    public void give(@NotNull Player player, double amount) {
        EconomyProvider.get().deposit(player.getUniqueId(), amount, this.currency);
    }

    @Override
    public void give(@NotNull UUID playerId, double amount) {
        EconomyProvider.get().deposit(playerId, amount, this.currency);
    }

    @Override
    public void take(@NotNull Player player, double amount) {
        EconomyProvider.get().withdraw(player.getUniqueId(), amount, this.currency);
    }

    @Override
    public void take(@NotNull UUID playerId, double amount) {
        EconomyProvider.get().deposit(playerId, amount, this.currency);
    }

}
