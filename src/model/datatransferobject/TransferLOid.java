package model.datatransferobject;

public class TransferLOid extends LO {
	private String idSlot;

	public void setIdSlot(String idSlot) {
		this.idSlot = idSlot;
	}

	public String getIdSlot() {
		return idSlot;
	}
	
	public TransferLOid(){
		super();
	}
	
	public TransferLOid(LO lo){
		super(lo);
	}
	
	/**
	 * Constructor vacío
	 * @param id nombre del LO
	 */
	public TransferLOid(String id) {
		super(id);
	}
	public boolean equals(TransferLOid lo){
		if(!(lo instanceof TransferLOid))
			return false;		
		TransferLOid other = (TransferLOid)lo;
		String idOther = other.getId();
		boolean b= getId().toLowerCase().equals(idOther .toLowerCase());
		//return (other.getId().equals(getId()));	
		return b;
		
	}
	public String toString(){
		return super.toString()+idSlot;
	}
}
