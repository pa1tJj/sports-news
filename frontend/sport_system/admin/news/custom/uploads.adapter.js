const IMAGE_URL = "http://localhost:8026/images/";

export class MyUploadAdapter {
    constructor(loader) {
        this.loader = loader;
    }

    upload() {
        return this.loader.file.then(file => {
            const data = new FormData();
            data.append('file', file);

            return fetch('http://localhost:8026/api/admin/news/uploads', {
                method: 'POST',
                body: data
            })
            .then(res => res.json())
            .then(result => {
                console.log(IMAGE_URL + result.url);
                return {
                    default: IMAGE_URL + result.url 
                };
            })
            .catch(err => {
                console.error(err);
            });
        });
    }
}
