package db;

// This class is important to avoid the Referential Integrity
// That means when a table is related with other, so
// when I delete, for example, one cell of category,
// this table can be affect the other table.

public class DbIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}
}
