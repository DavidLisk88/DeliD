package com.pluralsight.ui;

public class UIHelper {

    public static void printBoxedTitle(String title, int width) {
        if (width < title.length() + 4) {
            width = title.length() + 4;
        }
        // Top border: ╔═…═╗
        System.out.print("╔");
        for (int i = 0; i < width - 2; i++) {
            System.out.print("═");
        }
        System.out.println("╗");

        // Middle: ║  title centered  ║
        int paddingTotal = width - 2 - title.length();
        int padLeft = paddingTotal / 2;
        int padRight = paddingTotal - padLeft;
        System.out.print("║");
        for (int i = 0; i < padLeft; i++) System.out.print(" ");
        System.out.print(title);
        for (int i = 0; i < padRight; i++) System.out.print(" ");
        System.out.println("║");

        // Bottom border: ╚═…═╝
        System.out.print("╚");
        for (int i = 0; i < width - 2; i++) {
            System.out.print("═");
        }
        System.out.println("╝");
    }

    /**
     * Print a simple horizontal divider of length `width`, e.g. ──── or ──────.
     */
    public static void printHorizontalDivider(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("─");
        }
        System.out.println();
    }

    /**
     * Print a menu option inside square brackets, e.g. [1) Add Sandwich].
     * Automatically pads to `width` characters inside the brackets.
     */
    public static void printMenuOption(String label, int width) {
        // label might be "1) Add Sandwich"
        String inside = String.format(" %s ", label);
        if (inside.length() > width) {
            System.out.println("[" + inside + "]"); // no padding if too long
            return;
        }
        int padding = width - inside.length();
        int padLeft = padding / 2;
        int padRight = padding - padLeft;
        System.out.print("[");
        for (int i = 0; i < padLeft; i++) System.out.print(" ");
        System.out.print(inside);
        for (int i = 0; i < padRight; i++) System.out.print(" ");
        System.out.println("]");
    }
}

