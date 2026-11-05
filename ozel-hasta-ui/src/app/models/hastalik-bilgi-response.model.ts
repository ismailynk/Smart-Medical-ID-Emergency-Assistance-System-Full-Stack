import { HastaDTO } from "./hasta.model";
import { HastaHastalikBilgiResponse } from "./hasta-hastalik-bilgi-response.model";

export class HastalikBilgiResponse {
    hasta?: HastaDTO;
    hastaHastalik?: HastaHastalikBilgiResponse[];
}
