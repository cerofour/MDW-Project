package pe.edu.utp.CorefoSpringServer.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "editoriales")
@ToString
@EqualsAndHashCode
public class Editorial {

	@Id
	@Column(name = "id_editorial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@Column(name = "nombre")
	@Setter
	@Getter
	private String editorial;

	@OneToMany(mappedBy = "editorial", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<TextResource> texts;

	public Editorial() {
	}
}