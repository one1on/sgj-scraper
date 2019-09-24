## Welcome to sgjscraper

## Usage
The project consists of a scraper written in Java and a view for the images based on jekll with docker.
Clone the project:
```
git clone https://github.com/one1on/sgj-scraper
```
Run the scraper:
```
gradlew run
```

## Requirements
Scraper:
- JDK1.8
- IDE

Viewer:
- npm 
- docker

## Scraping Process
The scraping process does download all images for
1. Notifications for your subscribed hashtags
2. Feed entries

The scraped images are grouped by
1. Notifications: `hashtag_${hashtag}`
2. Feed entries: `${hashtag}`

Examples: 
1. A Notification for a subscribed hashtag 'curls' are grouped in a directory `hashtag_curls`
2. A Feed entry is grouped by the hashtags mentioned in the comments. If someone tagged an image with `#curls` in the comments then the image is stored in the directory `curls`.

## Gallery

The project contains of a basic gallery viewer based on jekyll (static page generator). The stored images are analysed and a simple website is generated for them.

The viewer is located in the `site` directory.
To get started run 
```
cd site
npm i
docker-compose up
```

### Galleries Overview
The scraped images are displayed and grouped by their directories. 
In this example there is a directory `france` with 2 images and a directory `none` with 3 images.

[![this screenshot](/assets/images/1_tn.jpg)](/assets/images/1.PNG)

By clicking on a image you get a slideshow for all images.
By clicking on the link you get the view for a single gallery.

### Slideshow
The slideshow allows to view all images easily.

[![this screenshot](/assets/images/2_tn.jpg)](/assets/images/2.PNG)

### Single Gallery
All images of this gallery are listed.
By clicking on a image you get a slideshow for the images.

[![this screenshot](/assets/images/3_tn.jpg)](/assets/images/3.PNG)

