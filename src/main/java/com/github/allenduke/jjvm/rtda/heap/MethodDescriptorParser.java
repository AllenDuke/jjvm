package com.github.allenduke.jjvm.rtda.heap;


/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/26
 */
public class MethodDescriptorParser {

    private String raw;

    private int offset;

    private MethodDescriptor parsed;


    public static MethodDescriptor parseMethodDescriptor(String descriptor) {
        MethodDescriptorParser parser = new MethodDescriptorParser();
        return parser.parse(descriptor);
    }

    private MethodDescriptor parse(String descriptor) {
        this.raw = descriptor;
        this.parsed = new MethodDescriptor();
        this.startParams();
        this.parseParamTypes();
        this.endParams();
        this.parseReturnType();
        this.finish();
        return parsed;
    }

    private void finish() {
        if (this.offset != this.raw.length()) this.causePanic();
    }

    private void causePanic() {
        throw new RuntimeException("BAD descriptor: " + this.raw);
    }

    private void parseReturnType() {
        if (this.readChar() == 'V') {
            this.parsed.setReturnType("V");
            return;
        }

        this.unReadChar();

        String fieldType = this.parseFieldType();
        if (!fieldType.equals("")) {
            this.parsed.setReturnType(fieldType);
            return;
        }

        this.causePanic();
    }

    private String parseFieldType() {
        switch (this.readChar()) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return this.parseObjectType();
            case '[':
                return this.parseArrayType();
            default:
                this.unReadChar();
                return "";
        }
    }

    private String parseArrayType() {
        int arrStart = this.offset - 1;
        this.parseFieldType();
        int arrEnd = this.offset;
        String descp = this.raw.substring(arrStart, arrEnd);
        return descp;
    }

    private String parseObjectType() {
        String unread = this.raw.substring(this.offset);
        int semicolonIndex = unread.indexOf(';');
        if (semicolonIndex == -1) {
            this.causePanic();
            return "";
        } else {
            int objStart = this.offset - 1;
            int objEnd = this.offset + semicolonIndex + 1;
            this.offset = objEnd;
            String descp = this.raw.substring(objStart, objEnd);
            return descp;
        }
    }

    private char readChar() {
        return this.raw.charAt(this.offset++);
    }

    private void unReadChar() {
        this.offset--;
    }

    private void endParams() {
        if (this.readChar() != ')') this.causePanic();
    }

    private void parseParamTypes() {
        while (true) {
            String fieldType = this.parseFieldType();
            if (!fieldType.equals(""))
                this.parsed.addParameterType(fieldType);
            else break;
        }
    }

    private void startParams() {
        if (this.readChar() != '(') {
            this.causePanic();
        }

    }

}
