# Welcome to sgjscraper

The project consists of a **scraper** written in Java and a **viewer** for the images.

## Setup
Clone the project:
```
git clone https://github.com/one1on/sgj-scraper
```

### .env
You will find a `.env.dist` in the repository. Copy this file to `.env` and fill the `SGJ_TOKEN` field.
If you run the application with docker do not edit `IMG_LOCATION`.
{% highlight yml %}
# Your SGJ token
SGJ_TOKEN=
# (optional) A location for the downloads. I prefer an encrypted virtual drive
IMG_LOCATION=
# (optional) Set a hashtag to subscribe to. Should be left empty after subscribtion. Subscribtion can be done on SGJ directly too.
HASHTAG_SUBSCRIBE=
{% endhighlight %}

If you really want to edit `IMG_LOCATION` to download images to an encrypted virtual drive manual changes in the `docker-compose.yml` are required.

## Usage
There are two ways to run the application
### 1. gradle + docker viewer + docker docs (development)
This method is used primarily for development.
In this setting the scraper is executed on the host while the viewer is started with docker.
#### Requirements
Scraper:
- JDK1.8
- IDE

Viewer:
- docker

Run the scraper:
```
gradlew run
```
Run the viewer. () means optional:
```
docker-compose up viewer (docs)
```
```
viewer: http://docker:8000
(docs: http://docker:8080)
```
### 2. dockerized app (primary)
In this setting the scraper is built and executed in docker. The viewer also runs in docker.
#### Requirements
Scraper:
- docker

Viewer:
- docker

To run the application with docker you have to build the images on your own since I can't provide them publicly.
It's really easy:
First build the images (we can do this in parallel).
```
docker-compose build --parallel
```
Make sure to enter a valid token in .env (see .env section).

Then boot the application (first boot takes some time because nothing is cached)
```
docker-compose up
```
To shut down the application press `CTRL+C`.
After a while try to access
```
viewer: http://<yourdockerip>:8000
docs: http://<yourdockerip>:8080
```

If you do not want to scrape and just view the images you can start the viewer only.
```
docker-compose up viewer
```

## Scraper
The scraping process does download all images for
1. Notifications for your subscribed hashtags
2. Feed entries

The scraped images are grouped by
1. Notifications: `hashtag_${hashtag}`
2. Feed entries: `${hashtag}`

Examples: 
1. A Notification for a subscribed hashtag 'curls' are grouped in a directory `hashtag_curls`
2. A Feed entry is grouped by the hashtags mentioned in the comments. If someone tagged an image with `#curls` in the comments then the image is stored in the directory `curls`.

## Viewer

The project contains of a basic gallery viewer based on jekyll (static page generator). The stored images are analysed and a simple website is generated for them.
The viewer is available under:
```
viewer: http://<yourdockerip>:8000
```

### Galleries Overview
The scraped images are displayed and grouped by their directories. 
In this example there is a directory `france` with 2 images and a directory `none` with 3 images.

[![this screenshot]({{ '/assets/images/1_tn.jpg' | relative_url }})]({{ '/assets/images/1.PNG' | relative_url }})

By clicking on a image you open a slideshow for all images.
By clicking on the link you open the view for a single gallery.

### Slideshow
The slideshow allows to view all images easily.

[![this screenshot]({{ '/assets/images/2_tn.jpg' | relative_url }})]({{ '/assets/images/2.PNG' | relative_url }})

### Single Gallery
All images of this gallery are listed.
By clicking on a image you open a slideshow for the images.

[![this screenshot]({{ '/assets/images/3_tn.jpg' | relative_url }})]({{ '/assets/images/3.PNG' | relative_url }})
