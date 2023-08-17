package nz.phan.augment.entity

class Model(var id: Long, var name: String,
            var categoryName: String,
            var imageId: Int? = null,
            var imageUriAsString: String? = null,
            var description: String,
            var wikipediaUri: String = "",
            var arPlacement: ArPlacement? = null)


