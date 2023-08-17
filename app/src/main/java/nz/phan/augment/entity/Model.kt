package nz.phan.augment.entity

class Model(var id: Long,
            var name: String,
            var category: String,
            var imageResId: Int? = null,
            var imageUriAsString: String? = null,
            var description: String,
            var wikipediaUri: String = "",
            var arPlacement: ArPlacement? = null)

class ModelResource(var id: Long,
                    var nameResId: Int,
                    var categoryResId: Int,
                    var imageResId: Int? = null,
                    var imageUriAsStringResId: Int? = null,
                    var descriptionResId: Int,
                    var wikipediaUriResId: Int,
                    var arPlacement: ArPlacement? = null)


inline fun ModelResource.toModel(action: (Int) -> String): Model {
     return Model(
        id = this.id,
        name = action(this.nameResId),
        category = action(this.categoryResId),
        imageResId = this.imageResId,
        imageUriAsString = if (this.imageUriAsStringResId == null) null else action(
            this.imageUriAsStringResId!!
        ),
        description = action(this.descriptionResId),
        wikipediaUri = action(this.wikipediaUriResId),
        arPlacement = this.arPlacement
    )
}
