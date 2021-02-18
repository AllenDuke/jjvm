package com.github.allenduke.avm.clazz.field;

import com.github.allenduke.avm.clazz.attribute.AttributeInfo;
import com.github.allenduke.avm.clazz.attribute.Code;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldInfo {

    //U2
    private String accessFlag;

    //U2
    private String name;

    //U2
    private String descriptor;

    private int attributesCount;

    private AttributeInfo[] attributes;


    public String accessFlagsToString(String s) {
        StringBuilder builder = new StringBuilder();
        switch (s.charAt(3)) {
            case '0':
                break;
            case '1':
                builder.append("ACC_PUBLIC").append("    ");
                break;
            case '2':
                builder.append("ACC_PRIVATE").append("    ");
                break;
            case '4':
                builder.append("ACC_PROTECTED").append("    ");
                break;
            case '8':
                builder.append("ACC_STATIC").append("    ");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        switch (s.charAt(2)) {
            case '0':
                break;
            case '1':
                builder.append("ACC_FINAL").append("    ");
                break;
            case '4':
                builder.append("ACC_VOLATILE").append("    ");
                break;
            case '8':
                builder.append("ACC_TRANSIENT").append("    ");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        switch (s.charAt(1)) {
            case '0':
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        switch (s.charAt(0)) {
            case '0':
                break;
            case '1':
                builder.append("ACC_SYNTHETIC");
                break;
            case '4':
                builder.append("ACC_ENUM");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        return builder.toString();
    }

    public Code getCodeAttribute() {
        for (AttributeInfo attribute_info : attributes) {
            if (attribute_info instanceof Code) {
                return (Code) attribute_info;
            }
        }
        return null;
    }

}
