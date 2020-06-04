package com.khmelenko.lab.varis.log;

final class TravisCommandLeaf implements LogEntryComponent {

    private final String mCommand;
    private final String mType;
    private final String mName;

    TravisCommandLeaf(final String command, final String type, final String name) {
        mCommand = command;
        mType = type;
        mName = name;
    }

    public String getCommand() {
        return mCommand;
    }

    public String getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toHtml() {
        return "";
    }
}
