import { MyUploadAdapter } from "./custom/uploads.adapter.js";

function MyCustomUploadAdapterPlugin(editor) {
    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
        return new MyUploadAdapter(loader);
    };
}

export function ckeditorConfig(callback) {

    ClassicEditor.create(document.querySelector("#content"), {
      extraPlugins: [MyCustomUploadAdapterPlugin],
      toolbar: [
        "heading",
        "|",
        "bold",
        "italic",
        "|",
        "link",
        "bulletedList",
        "numberedList",
        "outdent",
        "indent",
        "|",
        "imageUpload",
        "blockQuote",
        "insertTable",
        "mediaEmbed",
        "|",
        "undo",
        "redo",
      ],
      image: {
            toolbar: [
                "imageTextAlternative",
                "imageStyle:inline",
                "imageStyle:block",
                "imageStyle:side",
                "|",
                "resizeImage"
            ]
      }
    })
        .then((editor) => {
        callback(editor);
        }) 
        .catch((error) => console.error(error));
}