package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubHeadDetailsDTO {

    private Long account_Sub_Head_Id;
    private Long account_Main_Head_Id;
    private Long account_Type_Id;
    private String account_Main_Head;
    private String account_Sub_Head;
    private String account_Type;

    // Constructor matching the fields returned by the query
    public SubHeadDetailsDTO(Long accountSubHeadId, Long accountMainHeadId, Long accountTypeId, String accountSubHead, String accountType) {
        this.account_Sub_Head_Id = accountSubHeadId;
        this.account_Main_Head_Id = accountMainHeadId;
        this.account_Type_Id = accountTypeId;
        this.account_Sub_Head = accountSubHead;
        this.account_Type = accountType;
    }
}


