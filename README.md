coverflow-portlet
===================================

This portlet is a showcase of Cover Flow also known as Content Flow.

Most often we need a slider, carousel or some kind of animation. This portlet helps with setting up a content flow or cover flow for a site. 

All you need to do is create a folder in document library root folder and add images and their description. By default, cover flow supports showing 6 images which can be increased by configuration or key in portlet.properties file. You can also select the folder containing images in portlet configuration.


Steps to deploy and use:
========================
This porltet deploys just like other hot deployable plugins of Liferay.<br/>
1. If you have the source code, build it to a war file.<br/>
2. Put the package file in deploy folder of Liferay Portal.<br/>
3. Create a folder in document library of the site and add images along with description.<br/>
4. Add the portlet to a page. <br/>
5. Open portlet configuration and set image folder and number of images to be used in cover flow. <br/>
6. Now the portlet should show the coverflow as expected.<br/>
7. For any change that you need, you are free to modify anything in js files loaded with portlet.<br/>
