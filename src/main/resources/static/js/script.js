/**
 *  사용자가 원하는 이미지 다운로드 받기
 *  S3 이미지 접근
 */
async function downloadImagesAsZip() {
  const imageContainer = document.getElementById("imageContainer");
  const imageUrls = imageContainer.dataset.urls.replace(/\[|\]/g, "").split(', ').map(url => url.trim());
  const assetId = document.getElementById("imageContainer").dataset.assetId;
  const zip = new JSZip();

  try {
    // Fetch all images through the proxy
    const imageBlobs = await Promise.all(
      imageUrls.map(async (url, index) => {
        try {
          const proxyUrl = `/proxy?url=${encodeURIComponent(url)}`;
          const response = await fetch(proxyUrl);
          if (!response.ok) throw new Error(`Failed to fetch ${url}`);

          let devidedImageName = url.split("/"); // Extract the image name from the URL
          devidedImageName = devidedImageName[devidedImageName.length - 1];
          let imageNameArray = devidedImageName.split("_");
          let imageName = ""
          for (let i = 1; i < imageNameArray.length; i++) {
            if (i == imageNameArray.length - 1) {
              imageName += imageNameArray[i];
            }
            else{
              imageName += imageNameArray[i] + "_";
            }
          }
          
          return { name: `${imageName}`, blob: await response.blob() };
        } catch (error) {
          console.error("Error downloading image:", error);
          return null;
        }
      })
    );

    // Filter out failed downloads
    imageBlobs.filter(img => img !== null).forEach(img => zip.file(img.name, img.blob));

    // add .obj
    const objResponse = await fetch(`/api/assets/obj?id=${assetId}`);
    if (objResponse.ok) {
      const objBlob = await objResponse.blob();
      zip.file("output.obj", objBlob);
    }

    // add .mtl
    const mtlResponse = await fetch(`/api/assets/mtl?id=${assetId}`);
    if (mtlResponse.ok) {
      const mtlBlob = await mtlResponse.blob();
      zip.file("output.mtl", mtlBlob);
    }

    // Generate ZIP file and trigger download
    const content = await zip.generateAsync({ type: "blob" });
    const a = document.createElement("a");
    a.href = URL.createObjectURL(content);
    a.download = "images.zip";
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  } catch (error) {
    console.error("Error creating ZIP file:", error);
  }
}