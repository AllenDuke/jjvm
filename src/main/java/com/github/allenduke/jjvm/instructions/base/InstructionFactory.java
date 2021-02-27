package com.github.allenduke.jjvm.instructions.base;

import com.github.allenduke.jjvm.instructions.comparisons.*;
import com.github.allenduke.jjvm.instructions.constants.ldc.ldc;
import com.github.allenduke.jjvm.instructions.constants.ldc.ldc2_w;
import com.github.allenduke.jjvm.instructions.constants.ldc.ldc_w;
import com.github.allenduke.jjvm.instructions.constants.nooperands.*;
import com.github.allenduke.jjvm.instructions.constants.operands.bipush;
import com.github.allenduke.jjvm.instructions.constants.operands.sipush;
import com.github.allenduke.jjvm.instructions.control.*;
import com.github.allenduke.jjvm.instructions.conversions.*;
import com.github.allenduke.jjvm.instructions.extended.goto_w;
import com.github.allenduke.jjvm.instructions.extended.ifnonnull;
import com.github.allenduke.jjvm.instructions.extended.ifnull;
import com.github.allenduke.jjvm.instructions.extended.wide;
import com.github.allenduke.jjvm.instructions.loads.array.*;
import com.github.allenduke.jjvm.instructions.loads.d.*;
import com.github.allenduke.jjvm.instructions.loads.f.*;
import com.github.allenduke.jjvm.instructions.loads.i.*;
import com.github.allenduke.jjvm.instructions.loads.l.*;
import com.github.allenduke.jjvm.instructions.loads.ref.*;
import com.github.allenduke.jjvm.instructions.math.*;
import com.github.allenduke.jjvm.instructions.math.add.dadd;
import com.github.allenduke.jjvm.instructions.math.add.fadd;
import com.github.allenduke.jjvm.instructions.math.add.iadd;
import com.github.allenduke.jjvm.instructions.math.add.ladd;
import com.github.allenduke.jjvm.instructions.math.and.iand;
import com.github.allenduke.jjvm.instructions.math.and.land;
import com.github.allenduke.jjvm.instructions.math.div.ddiv;
import com.github.allenduke.jjvm.instructions.math.div.fdiv;
import com.github.allenduke.jjvm.instructions.math.div.idiv;
import com.github.allenduke.jjvm.instructions.math.div.ldiv;
import com.github.allenduke.jjvm.instructions.math.mul.dmul;
import com.github.allenduke.jjvm.instructions.math.mul.fmul;
import com.github.allenduke.jjvm.instructions.math.mul.imul;
import com.github.allenduke.jjvm.instructions.math.mul.lmul;
import com.github.allenduke.jjvm.instructions.math.neg.dneg;
import com.github.allenduke.jjvm.instructions.math.neg.fneg;
import com.github.allenduke.jjvm.instructions.math.neg.ineg;
import com.github.allenduke.jjvm.instructions.math.neg.lneg;
import com.github.allenduke.jjvm.instructions.math.or.ior;
import com.github.allenduke.jjvm.instructions.math.or.lor;
import com.github.allenduke.jjvm.instructions.math.rem.drem;
import com.github.allenduke.jjvm.instructions.math.rem.frem;
import com.github.allenduke.jjvm.instructions.math.rem.irem;
import com.github.allenduke.jjvm.instructions.math.rem.lrem;
import com.github.allenduke.jjvm.instructions.math.shl.ishl;
import com.github.allenduke.jjvm.instructions.math.shl.lshl;
import com.github.allenduke.jjvm.instructions.math.shr.ishr;
import com.github.allenduke.jjvm.instructions.math.shr.iushr;
import com.github.allenduke.jjvm.instructions.math.shr.lshr;
import com.github.allenduke.jjvm.instructions.math.shr.lushr;
import com.github.allenduke.jjvm.instructions.math.sub.dsub;
import com.github.allenduke.jjvm.instructions.math.sub.fsub;
import com.github.allenduke.jjvm.instructions.math.sub.isub;
import com.github.allenduke.jjvm.instructions.math.sub.lsub;
import com.github.allenduke.jjvm.instructions.math.xor.ixor;
import com.github.allenduke.jjvm.instructions.math.xor.lxor;
import com.github.allenduke.jjvm.instructions.references.*;
import com.github.allenduke.jjvm.instructions.stack.*;
import com.github.allenduke.jjvm.instructions.stores.d.*;
import com.github.allenduke.jjvm.instructions.stores.f.*;
import com.github.allenduke.jjvm.instructions.stores.i.*;
import com.github.allenduke.jjvm.instructions.stores.l.*;
import com.github.allenduke.jjvm.instructions.stores.ref.*;


import java.util.HashMap;
import java.util.Map;

public class InstructionFactory {

    private static Map<Integer, Instruction> codeMap = new HashMap<>();

    private static Map<String, Instruction> nameMap = new HashMap<>();

    static {
        putInstruction(new dcmpg());
        putInstruction(new dcmpl());
        putInstruction(new fcmpg());
        putInstruction(new fcmpl());
        putInstruction(new if_acmpeq());
        putInstruction(new if_acmpne());
        putInstruction(new if_icmpeq());
        putInstruction(new if_icmpge());
        putInstruction(new if_icmpgt());
        putInstruction(new if_icmple());
        putInstruction(new if_icmplt());
        putInstruction(new if_icmpne());
        putInstruction(new ifeq());
        putInstruction(new ifge());
        putInstruction(new ifgt());
        putInstruction(new ifle());
        putInstruction(new iflt());
        putInstruction(new ifne());
        putInstruction(new lcmp());

        putInstruction(new aconst_null());
        putInstruction(new bipush());
        putInstruction(new dconst_0());
        putInstruction(new dconst_1());
        putInstruction(new fconst_0());
        putInstruction(new fconst_1());
        putInstruction(new fconst_2());
        putInstruction(new iconst_0());
        putInstruction(new iconst_1());
        putInstruction(new iconst_2());
        putInstruction(new iconst_3());
        putInstruction(new iconst_4());
        putInstruction(new iconst_5());
        putInstruction(new iconst_m1());
        putInstruction(new lconst_0());
        putInstruction(new lconst_1());
        putInstruction(new ldc());
        putInstruction(new ldc2_w());
        putInstruction(new ldc_w());
        putInstruction(new nop());
        putInstruction(new sipush());


        putInstruction(new areturn());
        putInstruction(new dreturn());
        putInstruction(new freturn());
        putInstruction(new goto_());
        putInstruction(new ireturn());
        putInstruction(new jsr());
        putInstruction(new lookupswitch());
        putInstruction(new lreturn());
        putInstruction(new ret());
        putInstruction(new return_());
        putInstruction(new tableswitch());


        putInstruction(new d2f());
        putInstruction(new d2i());
        putInstruction(new d2l());
        putInstruction(new f2d());
        putInstruction(new f2i());
        putInstruction(new f2l());
        putInstruction(new i2b());
        putInstruction(new i2c());
        putInstruction(new i2d());
        putInstruction(new i2f());
        putInstruction(new i2l());
        putInstruction(new i2s());
        putInstruction(new l2d());
        putInstruction(new l2f());
        putInstruction(new l2i());

        putInstruction(new goto_w());
        putInstruction(new ifnonnull());
        putInstruction(new ifnull());
        putInstruction(new wide());


        putInstruction(new aaload());
        putInstruction(new aload());
        putInstruction(new aload_0());
        putInstruction(new aload_1());
        putInstruction(new aload_2());
        putInstruction(new aload_3());
        putInstruction(new baload());
        putInstruction(new caload());
        putInstruction(new daload());
        putInstruction(new dload());
        putInstruction(new dload_0());
        putInstruction(new dload_1());
        putInstruction(new dload_2());
        putInstruction(new dload_3());
        putInstruction(new faload());
        putInstruction(new fload());
        putInstruction(new fload_0());
        putInstruction(new fload_1());
        putInstruction(new fload_2());
        putInstruction(new fload_3());
        putInstruction(new iaload());
        putInstruction(new iload());
        putInstruction(new iload_0());
        putInstruction(new iload_1());
        putInstruction(new iload_2());
        putInstruction(new iload_3());
        putInstruction(new laload());
        putInstruction(new lload());
        putInstruction(new lload_0());
        putInstruction(new lload_1());
        putInstruction(new lload_2());
        putInstruction(new lload_3());
        putInstruction(new saload());
        putInstruction(new xaload());


        putInstruction(new dadd());
        putInstruction(new ddiv());
        putInstruction(new dmul());
        putInstruction(new dneg());
        putInstruction(new drem());
        putInstruction(new dsub());
        putInstruction(new fadd());
        putInstruction(new fdiv());
        putInstruction(new fmul());
        putInstruction(new fneg());
        putInstruction(new frem());
        putInstruction(new fsub());
        putInstruction(new iadd());
        putInstruction(new iand());
        putInstruction(new idiv());
        putInstruction(new iinc());
        putInstruction(new imul());
        putInstruction(new ineg());
        putInstruction(new ior());
        putInstruction(new irem());
        putInstruction(new ishl());
        putInstruction(new ishr());
        putInstruction(new isub());
        putInstruction(new iushr());
        putInstruction(new ixor());
        putInstruction(new ladd());
        putInstruction(new land());
        putInstruction(new ldiv());
        putInstruction(new lmul());
        putInstruction(new lneg());
        putInstruction(new lor());
        putInstruction(new lrem());
        putInstruction(new lshl());
        putInstruction(new lshr());
        putInstruction(new lsub());
        putInstruction(new lushr());
        putInstruction(new lxor());


        putInstruction(new dup());
        putInstruction(new dup2());
        putInstruction(new dup2_x1());
        putInstruction(new dup2_x2());
        putInstruction(new dup_x1());
        putInstruction(new dup_x2());
        putInstruction(new pop());
        putInstruction(new pop2());
        putInstruction(new swap());

        putInstruction(new astore());
        putInstruction(new astore_0());
        putInstruction(new astore_1());
        putInstruction(new astore_2());
        putInstruction(new astore_3());
        putInstruction(new dstore());
        putInstruction(new dstore_0());
        putInstruction(new dstore_1());
        putInstruction(new dstore_2());
        putInstruction(new dstore_3());
        putInstruction(new fstore());
        putInstruction(new fstore_0());
        putInstruction(new fstore_1());
        putInstruction(new fstore_2());
        putInstruction(new fstore_3());
        putInstruction(new istore());
        putInstruction(new istore_0());
        putInstruction(new istore_1());
        putInstruction(new istore_2());
        putInstruction(new istore_3());
        putInstruction(new lstore());
        putInstruction(new lstore_0());
        putInstruction(new lstore_1());
        putInstruction(new lstore_2());
        putInstruction(new lstore_3());

        putInstruction(new ldc());
        putInstruction(new ldc_w());
        putInstruction(new ldc2_w());

        putInstruction(new new_());
        putInstruction(new checkcast());
        putInstruction(new getfield());
        putInstruction(new getstatic());
        putInstruction(new instanceof_());
        putInstruction(new invokespecial());
        putInstruction(new invokevirtual());
        putInstruction(new putfield());
        putInstruction(new putstatic());

        putInstruction(new invokestatic());

    }

    public static Instruction getByOpcode(int opcode) throws Exception {
        Instruction instruction = codeMap.get(opcode);
        if (instruction==null){
            throw new Exception("no operation code");
        }
        return instruction;
    }

    public static Instruction getByOpcode(String name) throws Exception {
        Instruction instruction = nameMap.get(name);
        if (instruction==null){
            throw new Exception("no operation code");
        }
        return instruction;
    }

    private static void putInstruction(Instruction instruction) {
        codeMap.put(instruction.getOpCode(), instruction);
        nameMap.put(instruction.getReName(), instruction);
    }
}
