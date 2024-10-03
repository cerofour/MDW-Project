import React, { useState } from "react";
import { Form, Button, Container, Col, Row } from "react-bootstrap";
import { useMutation } from "@tanstack/react-query";

function CRUD() {
	// Estado para cada campo del formulario
	const [title, setTitle] = useState("");
	const [publicationDate, setPublicationDate] = useState("");
	const [stock, setStock] = useState(0);
	const [pages, setPages] = useState(0);
	const [edition, setEdition] = useState(0);
	const [volume, setVolume] = useState(0);
	const [textTypeId, setTextTypeId] = useState(0);
	const [textType, setTextType] = useState("");
	const [editorialId, setEditorialId] = useState(0);
	const [editorialName, setEditorialName] = useState("");
	const [authors, setAuthors] = useState([{ id: 0, alias: "", name: "", plastName: "", mlastName: "" }]);

	// Definir la mutación para agregar un nuevo TextResource
	const mutation = useMutation({
		mutationFn: async (newTextResource) => {
			const response = await fetch("http://localhost:8080/api/texts", {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(newTextResource),
			});
			if (!response.ok) {
				throw new Error("Error al agregar el recurso textual");
			}
			return response.json();
		},
		onSuccess: (data) => {
			alert("Text Resource created successfully!");
			console.log(data);
		},
		onError: (error) => {
			console.error("There was an error creating the Text Resource!", error);
		},
	});

	// Manejar el envío del formulario
	const handleSubmit = (e) => {
		e.preventDefault();

		// Crear el objeto que se enviará al servidor
		const newTextResource = {
			title,
			publication_date: publicationDate,
			stock,
			pages,
			edition,
			volume,
			type: { id: textTypeId, text_type: textType },
			editorial: { id: editorialId, editorial: editorialName },
			writtenBy: authors.map((author) => ({
				id: author.id,
				alias: author.alias,
				name: author.name,
				plastName: author.plastName,
				mlastName: author.mlastName,
			})),
		};

		// Ejecutar la mutación para enviar la solicitud POST
		mutation.mutate(newTextResource);
	};

	// Manejar el cambio en los campos de autor
	const handleAuthorChange = (index, field, value) => {
		const updatedAuthors = [...authors];
		updatedAuthors[index][field] = value;
		setAuthors(updatedAuthors);
	};

	// Añadir un nuevo autor al array
	const addAuthor = () => {
		setAuthors([...authors, { id: 0, alias: "", name: "", plastName: "", mlastName: "" }]);
	};

	return (
		<Container>
			<Row className="justify-content-md-center">
				<Col md="8">
					<h2 className="my-4">Add New Text Resource</h2>
					<Form onSubmit={handleSubmit}>
						{/* Campo para Título */}
						<Form.Group controlId="title">
							<Form.Label>Title</Form.Label>
							<Form.Control
								type="text"
								placeholder="Enter title"
								value={title}
								onChange={(e) => setTitle(e.target.value)}
								required
							/>
						</Form.Group>

						{/* Campo para Fecha de Publicación */}
						<Form.Group controlId="publicationDate" className="mt-3">
							<Form.Label>Publication Date</Form.Label>
							<Form.Control
								type="date"
								value={publicationDate}
								onChange={(e) => setPublicationDate(e.target.value)}
								required
							/>
						</Form.Group>

						{/* Campo para Stock */}
						<Form.Group controlId="stock" className="mt-3">
							<Form.Label>Stock</Form.Label>
							<Form.Control
								type="number"
								value={stock}
								onChange={(e) => setStock(parseInt(e.target.value) || 0)}
								required
							/>
						</Form.Group>

						{/* Campo para Número de Páginas */}
						<Form.Group controlId="pages" className="mt-3">
							<Form.Label>Pages</Form.Label>
							<Form.Control
								type="number"
								value={pages}
								onChange={(e) => setPages(parseInt(e.target.value) || 0)}
								required
							/>
						</Form.Group>

						{/* Campo para Edición */}
						<Form.Group controlId="edition" className="mt-3">
							<Form.Label>Edition</Form.Label>
							<Form.Control
								type="number"
								value={edition}
								onChange={(e) => setEdition(parseInt(e.target.value) || 0)}
								required
							/>
						</Form.Group>

						{/* Campo para Volumen */}
						<Form.Group controlId="volume" className="mt-3">
							<Form.Label>Volume</Form.Label>
							<Form.Control
								type="number"
								value={volume}
								onChange={(e) => setVolume(parseInt(e.target.value) || 0)}
								required
							/>
						</Form.Group>

						{/* Campos para Tipo de Texto */}
						<h4 className="mt-4">Text Type</h4>
						<Form.Group controlId="textTypeId" className="mt-3">
							<Form.Label>Text Type ID</Form.Label>
							<Form.Control
								type="number"
								value={textTypeId}
								onChange={(e) => setTextTypeId(parseInt(e.target.value) || 0)}
								required
							/>
						</Form.Group>
						<Form.Group controlId="textType" className="mt-3">
							<Form.Label>Text Type</Form.Label>
							<Form.Control
								type="text"
								placeholder="Enter text type"
								value={textType}
								onChange={(e) => setTextType(e.target.value)}
								required
							/>
						</Form.Group>

						{/* Campos para Editorial */}
						<h4 className="mt-4">Editorial</h4>
						<Form.Group controlId="editorialId" className="mt-3">
							<Form.Label>Editorial ID</Form.Label>
							<Form.Control
								type="number"
								value={editorialId}
								onChange={(e) => setEditorialId(parseInt(e.target.value) || 0)}
								required
							/>
						</Form.Group>
						<Form.Group controlId="editorialName" className="mt-3">
							<Form.Label>Editorial Name</Form.Label>
							<Form.Control
								type="text"
								placeholder="Enter editorial name"
								value={editorialName}
								onChange={(e) => setEditorialName(e.target.value)}
								required
							/>
						</Form.Group>

						{/* Campos para Autores */}
						<h4 className="mt-4">Authors</h4>
						{authors.map((author, index) => (
							<div key={index} className="border p-3 my-2">
								<Form.Group controlId={`authorId-${index}`} className="mt-3">
									<Form.Label>Author ID</Form.Label>
									<Form.Control
										type="number"
										value={author.id}
										onChange={(e) => handleAuthorChange(index, "id", parseInt(e.target.value) || 0)}
										required
									/>
								</Form.Group>
								<Form.Group controlId={`alias-${index}`} className="mt-3">
									<Form.Label>Alias</Form.Label>
									<Form.Control
										type="text"
										placeholder="Enter alias"
										value={author.alias}
										onChange={(e) => handleAuthorChange(index, "alias", e.target.value)}
										required
									/>
								</Form.Group>
								<Form.Group controlId={`name-${index}`} className="mt-3">
									<Form.Label>First Name</Form.Label>
									<Form.Control
										type="text"
										placeholder="Enter first name"
										value={author.name}
										onChange={(e) => handleAuthorChange(index, "name", e.target.value)}
										required
									/>
								</Form.Group>
								<Form.Group controlId={`plastName-${index}`} className="mt-3">
									<Form.Label>Last Name</Form.Label>
									<Form.Control
										type="text"
										placeholder="Enter last name"
										value={author.plastName}
										onChange={(e) => handleAuthorChange(index, "plastName", e.target.value)}
										required
									/>
								</Form.Group>
								<Form.Group controlId={`mlastName-${index}`} className="mt-3">
									<Form.Label>Middle Name</Form.Label>
									<Form.Control
										type="text"
										placeholder="Enter middle name"
										value={author.mlastName}
										onChange={(e) => handleAuthorChange(index, "mlastName", e.target.value)}
										required
									/>
								</Form.Group>
							</div>
						))}
						{/* Botón para añadir nuevo autor */}
						<Button variant="secondary" className="mt-3" onClick={addAuthor}>
							Add Author
						</Button>

						{/* Botón para enviar el formulario */}
						<Button className="mt-4" variant="primary" type="submit">
							Add Text Resource
						</Button>
					</Form>
				</Col>
			</Row>
		</Container>
	);
}

export default CRUD;
