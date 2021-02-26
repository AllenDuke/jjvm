package com.github.allenduke.jjvm.instructions.extended;

import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.instructions.base.Instruction;
import com.github.allenduke.jjvm.instructions.base.InstructionFactory;
import com.github.allenduke.jjvm.instructions.loads.d.dload;
import com.github.allenduke.jjvm.instructions.loads.f.fload;
import com.github.allenduke.jjvm.instructions.loads.i.iload;
import com.github.allenduke.jjvm.instructions.loads.l.lload;
import com.github.allenduke.jjvm.instructions.loads.ref.aload;
import com.github.allenduke.jjvm.instructions.math.iinc;
import com.github.allenduke.jjvm.instructions.stores.d.dstore;
import com.github.allenduke.jjvm.instructions.stores.f.fstore;
import com.github.allenduke.jjvm.instructions.stores.i.istore;
import com.github.allenduke.jjvm.instructions.stores.l.lstore;
import com.github.allenduke.jjvm.instructions.stores.ref.astore;
import com.github.allenduke.jjvm.rtda.Frame;

/* 当局部变量表大小超过256时，使用wide指令扩展 */
public class wide implements Instruction {

    private Instruction modifiedInstruction;    /* 扩展后的指令，扩展后执行 */

    @Override
    public int getOpCode() {
        return CODE_wide;
    }

    @Override
    public void fetchOperands(BytecodeReader reader) throws Exception {
        int opcode = reader.readUInt8();            /* 要被扩展的指令 */
        // todo 优化代码
        switch (opcode) {                       /* 增加索引的宽度 */
            case CODE_iload:
                iload a = (iload) InstructionFactory.getByOpcode(CODE_iload);
                a.setIndex(reader.readUInt16());
                modifiedInstruction = a;
                break;
            case CODE_lload:
                lload b = (lload) InstructionFactory.getByOpcode(CODE_lload);
                b.setIndex(reader.readUInt16());
                modifiedInstruction = b;
                break;
            case CODE_fload:
                fload c = (fload) InstructionFactory.getByOpcode(CODE_fload);
                c.setIndex(reader.readUInt16());
                modifiedInstruction = c;
                break;
            case CODE_dload:
                dload d = (dload) InstructionFactory.getByOpcode(CODE_dload);
                d.setIndex(reader.readUInt16());
                modifiedInstruction = d;
                break;
            case CODE_aload:
                aload e = (aload) InstructionFactory.getByOpcode(CODE_aload);
                e.setIndex(reader.readUInt16());
                modifiedInstruction = e;
                break;
            case CODE_istore:
                istore f = (istore) InstructionFactory.getByOpcode(CODE_istore);
                f.setIndex(reader.readUInt16());
                modifiedInstruction = f;
                break;
            case CODE_lstore:
                lstore g = (lstore) InstructionFactory.getByOpcode(CODE_lstore);
                g.setIndex(reader.readUInt16());
                modifiedInstruction = g;
                break;
            case CODE_fstore:
                fstore h = (fstore) InstructionFactory.getByOpcode(CODE_fstore);
                h.setIndex(reader.readUInt16());
                modifiedInstruction = h;
                break;
            case CODE_dstore:
                dstore i = (dstore) InstructionFactory.getByOpcode(CODE_dstore);
                i.setIndex(reader.readUInt16());
                modifiedInstruction = i;
                break;
            case CODE_astore:
                astore j = (astore) InstructionFactory.getByOpcode(CODE_astore);
                j.setIndex(reader.readUInt16());
                modifiedInstruction = j;
                break;
            case CODE_iinc:
                iinc k = (iinc) InstructionFactory.getByOpcode(CODE_iinc);
                k.setIndex(reader.readUInt16());
                k.setConstVal(reader.readInt16());
                modifiedInstruction = k;
                break;
            case CODE_ret:
                throw new Exception("Unsupported opcode: 0xa9!");
        }
    }

    @Override
    public void execute(Frame frame) throws Exception {
        modifiedInstruction.execute(frame);
    }
}
