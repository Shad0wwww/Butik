package dk.shadow.buycraft.enums;

public enum Hook {
    AREASHOP(false);
    private final boolean isBuiltIn;

    Hook(boolean paramBoolean) {
        this.isBuiltIn = paramBoolean;
    }

    public boolean isBuiltIn() {
        return this.isBuiltIn;
    }
}
