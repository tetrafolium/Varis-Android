package com.khmelenko.lab.varis.log;

/**
 * Defines possible options for formatting
 */
final class FormattingOptions {

    private String mTextColor;
    private String mBackground;
    private boolean mBold;
    private boolean mItalic;
    private boolean mUnderline;

    // denied constructor
    private FormattingOptions() {

    }

    public static FormattingOptions fromAnsiCodes(final String... ansiStates) {
        FormattingOptions options = new FormattingOptions();
        for (String ansiCode : ansiStates) {
            AnsiCodes.applyAnsiCode(options, ansiCode);
        }
        return options;
    }

    public String getTextColor() {
        return mTextColor;
    }

    public void setTextColor(final String textColor) {
        mTextColor = textColor;
    }

    public String getBackground() {
        return mBackground;
    }

    public void setBackground(final String background) {
        mBackground = background;
    }

    public boolean isBold() {
        return mBold;
    }

    public void setBold(final boolean bold) {
        mBold = bold;
    }

    public boolean isItalic() {
        return mItalic;
    }

    public void setItalic(final boolean italic) {
        mItalic = italic;
    }

    public boolean isUnderline() {
        return mUnderline;
    }

    public void setUnderline(final boolean underline) {
        mUnderline = underline;
    }

    @Override
    public String toString() {
        return "FormattingOptions{"
                + "textColor=" + mTextColor
                + ", background=" + mBackground
                + ", bold=" + mBold
                + ", italic=" + mItalic
                + ", underline=" + mUnderline
                + '}';
    }
}
