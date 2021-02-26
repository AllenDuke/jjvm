package com.github.allenduke.jjvm.instructions.base;

import com.github.allenduke.jjvm.rtda.Frame;

public interface Instruction {

    /* constant类指令操作码 */
    int CODE_nop = 0x00;
    int CODE_aconst_null = 0x01;
    int CODE_iconst_m1 = 0x02;
    int CODE_iconst_0 = 0x03;
    int CODE_iconst_1 = 0x04;
    int CODE_iconst_2 = 0x05;
    int CODE_iconst_3 = 0x06;
    int CODE_iconst_4 = 0x07;
    int CODE_iconst_5 = 0x08;
    int CODE_lconst_0 = 0x09;
    int CODE_lconst_1 = 0x0a;
    int CODE_fconst_0 = 0x0b;
    int CODE_fconst_1 = 0x0c;
    int CODE_fconst_2 = 0x0d;
    int CODE_dconst_0 = 0x0e;
    int CODE_dconst_1 = 0x0f;
    int CODE_bipush = 0x10;
    int CODE_sipush = 0x11;
    int CODE_ldc = 0x12;
    int CODE_ldc_w = 0x13;
    int CODE_ldc2_w = 0x14;

    /* load类指令操作码 */
    int CODE_iload = 21;
    int CODE_lload = 22;
    int CODE_fload = 23;
    int CODE_dload = 24;
    int CODE_aload = 25;
    int CODE_iload_0 = 26;
    int CODE_iload_1 = 27;
    int CODE_iload_2 = 28;
    int CODE_iload_3 = 29;
    int CODE_lload_0 = 30;
    int CODE_lload_1 = 31;
    int CODE_lload_2 = 32;
    int CODE_lload_3 = 33;
    int CODE_fload_0 = 34;
    int CODE_fload_1 = 35;
    int CODE_fload_2 = 36;
    int CODE_fload_3 = 37;
    int CODE_dload_0 = 38;
    int CODE_dload_1 = 39;
    int CODE_dload_2 = 40;
    int CODE_dload_3 = 41;
    int CODE_aload_0 = 42;
    int CODE_aload_1 = 43;
    int CODE_aload_2 = 44;
    int CODE_aload_3 = 45;
    int CODE_iaload = 46;
    int CODE_laload = 47;
    int CODE_faload = 48;
    int CODE_daload = 49;
    int CODE_aaload = 50;
    int CODE_baload = 51;
    int CODE_caload = 52;
    int CODE_saload = 53;

    /* store类指令操作码 */
    int CODE_istore = 54;
    int CODE_lstore = 55;
    int CODE_fstore = 56;
    int CODE_dstore = 57;
    int CODE_astore = 58;
    int CODE_istore_0 = 59;
    int CODE_istore_1 = 60;
    int CODE_istore_2 = 61;
    int CODE_istore_3 = 62;
    int CODE_lstore_0 = 63;
    int CODE_lstore_1 = 64;
    int CODE_lstore_2 = 65;
    int CODE_lstore_3 = 66;
    int CODE_fstore_0 = 67;
    int CODE_fstore_1 = 68;
    int CODE_fstore_2 = 69;
    int CODE_fstore_3 = 70;
    int CODE_dstore_0 = 71;
    int CODE_dstore_1 = 72;
    int CODE_dstore_2 = 73;
    int CODE_dstore_3 = 74;
    int CODE_astore_0 = 75;
    int CODE_astore_1 = 76;
    int CODE_astore_2 = 77;
    int CODE_astore_3 = 78;
    int CODE_iastore = 79;
    int CODE_lastore = 80;
    int CODE_fastore = 81;
    int CODE_dastore = 82;
    int CODE_aastore = 83;
    int CODE_bastore = 84;
    int CODE_castore = 85;
    int CODE_sastore = 86;

    /* stack类指令操作码 */
    int CODE_pop = 87;
    int CODE_pop2 = 88;
    int CODE_dup = 89;
    int CODE_dup_x1 = 90;
    int CODE_dup_x2 = 91;
    int CODE_dup2 = 92;
    int CODE_dup2_x1 = 93;
    int CODE_dup2_x2 = 94;
    int CODE_swap = 95;

    /* math类指令操作码 */
    int CODE_iadd = 96;
    int CODE_ladd = 97;
    int CODE_fadd = 98;
    int CODE_dadd = 99;
    int CODE_isub = 100;
    int CODE_lsub = 101;
    int CODE_fsub = 102;
    int CODE_dsub = 103;
    int CODE_imul = 104;
    int CODE_lmul = 105;
    int CODE_fmul = 106;
    int CODE_dmul = 107;
    int CODE_idiv = 108;
    int CODE_ldiv = 109;
    int CODE_fdiv = 110;
    int CODE_ddiv = 111;
    int CODE_irem = 112;
    int CODE_lrem = 113;
    int CODE_frem = 114;
    int CODE_drem = 115;
    int CODE_ineg = 116;
    int CODE_lneg = 117;
    int CODE_fneg = 118;
    int CODE_dneg = 119;
    int CODE_ishl = 120;
    int CODE_lshl = 121;
    int CODE_ishr = 122;
    int CODE_lshr = 123;
    int CODE_iushr = 124;
    int CODE_lushr = 125;
    int CODE_iand = 126;
    int CODE_land = 127;
    int CODE_ior = 128;
    int CODE_lor = 129;
    int CODE_ixor = 130;
    int CODE_lxor = 131;
    int CODE_iinc = 132;

    /* conversion类指令操作码 */
    int CODE_i2l = 133;
    int CODE_i2f = 134;
    int CODE_i2d = 135;
    int CODE_l2i = 136;
    int CODE_l2f = 137;
    int CODE_l2d = 138;
    int CODE_f2i = 139;
    int CODE_f2l = 140;
    int CODE_f2d = 141;
    int CODE_d2i = 142;
    int CODE_d2l = 143;
    int CODE_d2f = 144;
    int CODE_i2b = 145;
    int CODE_i2c = 146;
    int CODE_i2s = 147;

    /* comparison类指令操作码 */
    int CODE_lcmp = 148;
    int CODE_fcmpl = 149;
    int CODE_fcmpg = 150;
    int CODE_dcmpl = 151;
    int CODE_dcmpg = 152;
    int CODE_ifeq = 153;
    int CODE_ifne = 154;
    int CODE_iflt = 155;
    int CODE_ifge = 156;
    int CODE_ifgt = 157;
    int CODE_ifle = 158;
    int CODE_if_icmpeq = 159;
    int CODE_if_icmpne = 160;
    int CODE_if_icmplt = 161;
    int CODE_if_icmpge = 162;
    int CODE_if_icmpgt = 163;
    int CODE_if_icmple = 164;
    int CODE_if_acmpeq = 165;
    int CODE_if_acmpne = 166;

    /* control类指令操作码 */
    int CODE_goto = 167;
    int CODE_jsr = 168;
    int CODE_ret = 169;
    int CODE_tableswitch = 170;
    int CODE_lookupswitch = 171;
    int CODE_ireturn = 172;
    int CODE_lreturn = 173;
    int CODE_freturn = 174;
    int CODE_dreturn = 175;
    int CODE_areturn = 176;
    int CODE_return = 177;

    /* reference类指令操作码 */
    int CODE_getstatic = 178;
    int CODE_putstatic = 179;
    int CODE_getfield = 180;
    int CODE_putfield = 181;
    int CODE_invokevirtual = 182;
    int CODE_invokespecial = 183;
    int CODE_invokestatic = 184;
    int CODE_invokeinterface = 185;
    int CODE_invokedynamic = 186;
    int CODE_new = 187;
    int CODE_newarray = 188;
    int CODE_anewarray = 189;
    int CODE_arraylength = 190;
    int CODE_athrow = 191;
    int CODE_checkcast = 192;
    int CODE_instanceof = 193;
    int CODE_monitorenter = 194;
    int CODE_monitorexit = 195;

    /* extend类指令操作码 */
    int CODE_wide = 196;
    int CODE_multianewarray = 197;
    int CODE_ifnull = 198;
    int CODE_ifnonnull = 199;
    int CODE_goto_w = 200;
    int CODE_jsr_w = 201;

    /* reserved类指令操作码 */
    int CODE_breakpoint = 202;
    int CODE_impdep1 = 254;
    int CODE_impdep2 = 255;

    int getOpCode();

    default String getReName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 从字节码中提取操作数
     *
     * @param reader
     */
    void fetchOperands(BytecodeReader reader) throws Exception;

    /**
     * 执行逻辑指令
     *
     * @param frame
     */
    void execute(Frame frame) throws Exception;
}
