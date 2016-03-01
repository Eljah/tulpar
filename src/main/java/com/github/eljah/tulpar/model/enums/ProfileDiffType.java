package com.github.eljah.tulpar.model.enums;

/**
 * Created by ilya on 01.03.16.
 */
public enum ProfileDiffType {

    PROFILE_DIFF_MANUAL (1),
    PROFILE_DIFF_REMOTE_COMMAND(3),
    PROFILE_DIFF_LOCAL_COMMAND(2),
    PROFILE_DIFF_GENERIC_PROGRAMMATICAL(0);

    private int value;

    ProfileDiffType(int value) { this.value = value; }

    public int getValue() { return value; }

    public static ProfileDiffType parse(int id) {
        ProfileDiffType right = null; // Default
        for (ProfileDiffType item : ProfileDiffType.values()) {
            if (item.getValue()==id) {
                right = item;
                break;
            }
        }
        return right;
    }
}
