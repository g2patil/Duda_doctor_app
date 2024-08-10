package model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


	@Entity
	@Table(uniqueConstraints={@UniqueConstraint(columnNames={"uid","bldg_name","bldg_loc"})})
		
	public class bldg {
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private long id;
	    @Column(length =255)
	    private String bldg_name;
	    @Column(length =255)
	    private String bldg_loc;
	   	@Column(length =5)
	    private long uid;
	    private Date entry_date;
	/*    private int bldg_floor;
	    @Column(length =3)
	    private String bldg_room_no;
	    @Column(length =5)
	    private int renter_id;
	    @Column(length =255)
	    private String renter_mob;
	    @Column(length =255)
	    private String renter_name;
	    @Column(length =255)
	    private String document_taken;
	    @Column(name = "lease_start_date")
	    @DateTimeFormat(pattern = "dd-MM-yyyy")
	    private Date lease_start_date;*/
	    
	    
		public long getUid() {
			return uid;
		}
		public void setUid(long uid) {
			this.uid = uid;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getBldg_name() {
			return bldg_name;
		}
		public void setBldg_name(String bldg_name) {
			this.bldg_name = bldg_name;
		}
		 public String getBldg_loc() {
				return bldg_loc;
			}
			public void setBldg_loc(String bldg_loc) {
				this.bldg_loc = bldg_loc;
			}
			public Date getEntry_date() {
				return entry_date;
			}
			public void setEntry_date(Date entry_date) {
				this.entry_date = entry_date;
			}
			
	}
