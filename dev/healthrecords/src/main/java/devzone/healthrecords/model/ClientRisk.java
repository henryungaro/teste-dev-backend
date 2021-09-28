package devzone.healthrecords.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRisk {
	private Long id;
	private String name;
	private double score;
	private static double e = 2.718281828459045235360287;
	
	public ClientRisk(Client client, double sumDegree)
	{
		this.id = client.getId();
		this.name = client.getName();
		double sd = sumDegree;
		double eulerPowered = Math.pow(e, -(-2.8 + sd));
		double finalResult = (1 /( 1 + eulerPowered)) * 100;
		this.score = finalResult;
	}
}
