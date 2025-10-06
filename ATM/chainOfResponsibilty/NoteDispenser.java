package ATM.chainOfResponsibilty;

abstract class NoteDispenser  implements DispenserChain{
    private DispenserChain nextChain;
    private final int noteValue;
    private int notes;
    public NoteDispenser(int noteValue,int notes){

        this.noteValue=noteValue;
        this.notes=notes;
    }

    @Override
    public synchronized boolean canDispense(int amount) {
        if(amount<0)
        return false;
        if(amount==0){
            return true;
        }
        int notesToUser=Math.min(amount/notes,noteValue);
        int remAmount=amount-(notesToUser*noteValue);
        if(remAmount==0){
            return true;
        }
        if(this.nextChain!=null){
            return this.nextChain.canDispense(remAmount);
        }
        return false;
    }

    @Override
    public synchronized void dispense(int amount) {
        if(amount>=noteValue){
            int notesToDispense=Math.min(amount/noteValue,notes);
            int remainingAmount=amount- (notesToDispense*noteValue);
            if(notesToDispense>0){
                         System.out.println("Dispensing "+notesToDispense + " "+"Rs "+noteValue);
            }
            if(remainingAmount>0&&this.nextChain!=null){
                    this.nextChain.dispense(remainingAmount);
            }

        }
        else if(this.nextChain!=null){
            this.nextChain.dispense(amount);
        }
        
    }

    @Override
    public void setNextChain(DispenserChain nextChain) {
        this.nextChain=nextChain;
        
    }

}
