import { HastaDTO } from "./hasta.model";
import { HastalikDTO } from "./hastalik.model";

export class HastaHastalikDTO {
    id?: string;
    hasta?: HastaDTO;
    hastalik?: HastalikDTO;
    teshisTarihi?: Date;
}
