export type BookAPIObject = {
	id: number;
	title: string;
	publication_date: string;
	stock: number;
	pages: number;
	edition: number;
	volume: number;
	type: { id: number; text_type: string };
	editorial: { id: number; editorial: string };
};

export type AuthorAPIObject = {
	id: number;
	name: string;
	mlastName: string;
	plastName: string;
	alias: string;
};

export type EditorialAPIObject = {
	id: number;
	editorial: string;
}

export type TextResourceTypeAPIObject = {
	id: number;
	type: string;
}
export async function fetchBookData() {
  const request = await fetch("http://localhost:8080/api/texts", {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });

  if (request.ok === false) throw new Error(`Failed request with \n${request}`);

  return request.json();
}

export async function fetchEditorials() {
  const request = await fetch("http://localhost:8080/api/editorials", {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });

  if (request.ok === false) throw new Error(`Failed request with \n${request}`);

  return request.json();
}

export async function fetchTextResourceTypes() {
  const request = await fetch("http://localhost:8080/api/text-resource-types", {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });

  if (request.ok === false) throw new Error(`Failed request with \n${request}`);

  return request.json();
}

export async function postTextResource() {
	const rawResponse = await fetch('https://httpbin.org/post', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({a: 1, b: 'Textual content'})
  });
  const content = await rawResponse.json();

  console.log(content);
}

export async function fetchAuthors() {
  const request = await fetch("http://localhost:8080/api/authors", {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });

  if (request.ok === false) throw new Error(`Failed request with \n${request}`);

  return request.json();
}