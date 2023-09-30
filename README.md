# Augment

Augment is an Android application with AR (augmented reality) capabilities aimed at children, parents and teachers. Augment brings the zoo to your own home and school and makes it more accessible than ever to learn about animals.
Augment starts by letting you pick from a catalogue of zoo animals. After you’ve selected one, you can then read through its information on a dedicated screen. Finally, you can enter AR mode which lets you place the realistic 3D animal in your real life environment. The first video attached is worth a watch of me demonstrating this feature :point_down:
These AR capabilities have been implemented using Google ARCore. I’m personally very enthused by VR/AR which is why I have chosen to implement this. Also, Augment allows you to create your own animal too, but with limited time I have decided not to support uploading a custom 3D model.

## Development process

### Feasibility study
I read a number of tutorials on how to implement AR and determined that it was feasible. Initially I wanted to implement AR so that it can detect human faces and apply filters (like Snapchat filters) and did a draft implementation of that app, however baking in face detection was too complex for the scope of the project so I settled on Augment.

### UI construction & navigation
I implemented the first two screens: Catalogue of animals and Single Animal view using the single activity - multiple fragment architecture. I also made it look nice by customising transition, font, color scheme and window inset (status bar & nav bar are transparent).

### AR
I then implemented AR animal viewer as a separate AppCompatActivity using good old ConstraintLayout that can be invoked via an explicit intent. The way the AR activity works is that you will add an animal to your environment, which will then move with your frame until you're satisfied. At that moment, you can then choose to fixate the animal in one place.
Because I am very enthused by Extended Reality/GPU programming this wasn't too hard to implement, but reading through a lot of documentation was needed.

### Add new animal
I implemented a new screen, Add new animal, that would allow the user to add their custom animals including a .glb 3D file with a file picker. However it was a bit of a rabbit hole with Room persistence etc. so I decided to U-turn and not offer (for now) AR capability for custom animals.

Finally, I added some bells and whistles like the compact disc animation, worked on translation, horizontal orientation, and haptic feedback.

### What I would have done differently
If I had more time I'd allow the user to upload their own image and 3d model and use Room persistence.